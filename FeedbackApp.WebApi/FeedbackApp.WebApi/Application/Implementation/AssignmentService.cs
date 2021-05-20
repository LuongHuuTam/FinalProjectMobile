using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.Utilities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class AssignmentService : IAssignmentService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public AssignmentService(FeedbackAppDbContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        public async Task<bool> AddAssignment(Assignment request)
        {
            request.RegistrationCode = RegistationCode.GetCode(request.ClassId, request.ModuleID);

            try
            {
                _context.Assignments.Add(request);
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> DeleteAssignment(Assignment request)
        {
            var res = await _context.Assignments.Where(x => x.ClassId == request.ClassId && x.ModuleID == request.ModuleID && x.TrainerID == request.TrainerID).FirstOrDefaultAsync();
            try
            {
                _context.Assignments.Remove(res);
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> EditAssignment(Assignment request)
        {
            var res = await _context.Assignments.Where(x => x.ClassId == request.ClassId && x.ModuleID == request.ModuleID).FirstOrDefaultAsync();
            if (res == null)
                return false;
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

        public async Task<List<AssignmentVm>> GetAll(string search)
        {
            var data = await _context.Assignments.Include(x => x.Class).Include(x => x.Module).Include(x => x.Trainer).ToListAsync();
            data = data.Where(x => x.Class.Name.Contains(search) ||
                                x.Module.Name.Contains(search) ||
                                x.Trainer.Name.Contains(search) ||
                                x.RegistrationCode.Contains(search)).ToList();
            return _mapper.Map<List<AssignmentVm>>(data);
        }

        public async Task<List<AssignmentVm>> GetAssignmentOfTrainer(string trainerId, string search)
        {
            var data = await _context.Assignments.Where(x => x.TrainerID == trainerId).Include(x => x.Class).Include(x => x.Module).Include(x => x.Trainer).ToListAsync();
            data = data.Where(x => x.Class.Name.Contains(search) ||
                                x.Module.Name.Contains(search) ||
                                x.Trainer.Name.Contains(search) ||
                                x.RegistrationCode.Contains(search)).ToList();
            return _mapper.Map<List<AssignmentVm>>(data);
        }

        public async Task<AssignmentVm> GetById(AssignmentRequest request)
        {
            var data = await _context.Assignments
                .Where(x => x.ModuleID == request.ModuleID && x.ClassId == request.ClassId && x.TrainerID == request.TrainerID)
                .Include(x => x.Class).Include(x => x.Module).Include(x => x.Trainer)
                .FirstOrDefaultAsync();
            return _mapper.Map<AssignmentVm>(data);
        }
    }
}
