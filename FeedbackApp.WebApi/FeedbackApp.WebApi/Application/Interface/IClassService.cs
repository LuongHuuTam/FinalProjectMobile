using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IClassService
    {
        Task<bool> AddClass(ClassVm request);
        Task<bool> EditClass(int classId, ClassVm request);
        Task<bool> DeleteClass(int classId);
        Task<List<ClassVm>> GetAll();
        Task<ClassVm> GetById(int classId);
        Task<List<TraineeVm>> TraineeInClass(int classId);
        Task<List<ClassVm>> ClassOfTrainee(string username);
        Task<List<ClassVm>> ClassOfTrainer(string username);
    }
}
