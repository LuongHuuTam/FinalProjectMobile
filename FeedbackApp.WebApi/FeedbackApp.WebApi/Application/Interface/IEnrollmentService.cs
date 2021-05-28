using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IEnrollmentService
    {
        Task<List<EnrollmentVm>> GetAll();
        Task<EnrollmentVm> GetById(int classId, string traineeId);
        Task<bool> Update(EnrollmentVm request);

        Task<string> Join(JoinRequest request);
    }
}
