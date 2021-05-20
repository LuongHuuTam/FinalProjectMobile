using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class ModuleService : IModuleService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public ModuleService(FeedbackAppDbContext context, IMapper mapper)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<bool> AddModule(ModuleVm moduleVm)
        {
            Module module = _mapper.Map<Module>(moduleVm);
            try
            {
                _context.Modules.Add(module);
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> DeleteModule(int moduleId)
        {
            var module = await _context.Modules.FindAsync(moduleId);
            if (module == null)
                return false;

            module.IsDeleted = true;
            try
            {
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> EditModule(int moduleId, ModuleVm request)
        {
            var module = await _context.Modules.FindAsync(moduleId);
            if (module == null)
                return false;

            module.Name = request.Name;
            module.StartTime = request.StartTime;
            module.EndTime = request.EndTime;
            module.AdminID = request.AdminID;
            module.FeedbackID = request.FeedbackID;
            module.FeedbackEndTime = request.FeedbackEndTime;
            module.FeedbackStartTime = request.FeedbackStartTime;
            try
            {
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<List<ModuleVm>> GetAll()
        {
            var data = await _context.Modules.Where(x => x.IsDeleted == false).Include(x => x.Feedback).ToListAsync();
            List<ModuleVm> res = _mapper.Map<List<Module>, List<ModuleVm>>(data);
            return res;
        }

        public async Task<ModuleVm> GetById(int moduleId)
        {
            var data = await _context.Modules.Where(x => x.IsDeleted == false && x.ModuleID == moduleId).Include(x => x.Feedback).FirstOrDefaultAsync();
            ModuleVm res = _mapper.Map<ModuleVm>(data);
            return res;
        }
    }
}
