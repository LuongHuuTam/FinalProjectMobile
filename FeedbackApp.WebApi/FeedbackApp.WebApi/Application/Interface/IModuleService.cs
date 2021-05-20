using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IModuleService
    {
        Task<List<ModuleVm>> GetAll();
        Task<ModuleVm> GetById(int moduleId);
        Task<bool> EditModule(int moduleId, ModuleVm request);
        Task<bool> AddModule(ModuleVm moduleVm);
        Task<bool> DeleteModule(int moduleId);
    }
}
