using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IAssignmentService
    {
        Task<bool> AddAssignment(Assignment request);
        Task<bool> EditAssignment(Assignment request);
        Task<bool> DeleteAssignment(Assignment request);
        Task<List<AssignmentVm>> GetAll(string search);
        Task<AssignmentVm> GetById(AssignmentRequest request);
        Task<List<AssignmentVm>> GetAssignmentOfTrainer(string trainerId, string search);
    }
}
