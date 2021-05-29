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
        Task<List<TypeFeedback>> GetTypeFeedback();
        Task<FeedbackVm> GetById(int feedbackId);
        Task<bool> Update(int feedbackId, FeedbackVm request);
        Task<bool> Add(FeedbackVm request);
        Task<bool> Delete(int feedbackId);

        Task<List<DoFeedbackVm>> GetDoFeedBack(string traineeId);
        Task<bool> AddAnswer(AnswerRequest request);
    }
}
