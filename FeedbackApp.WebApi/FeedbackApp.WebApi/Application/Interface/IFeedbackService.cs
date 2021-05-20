using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IFeedbackService
    {
        Task<List<FeedbackVm>> GetAll();

        Task<FeedbackVm> GetById(int feedbackId);

        Task<bool> Add(FeedbackVm request);
    }
}
