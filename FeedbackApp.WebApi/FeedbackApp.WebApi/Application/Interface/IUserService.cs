using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IUserService
    {
        Task<LoginResponse> Authenticate(LoginRequest request);
        Task<List<UserVm>> ListTrainee();
        Task<List<UserVm>> ListTrainer();
    }
}
