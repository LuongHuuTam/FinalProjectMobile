using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IQuestionService
    {
        Task<List<QuestionVm>> GetAll(int topicId);
        Task<QuestionVm> GetById(int questionId);
        Task<bool> AddQuestion(QuestionVm request);
        Task<bool> EditQuestion(int questionId, QuestionVm request);
        Task<bool> DeleteQuestion(int questionId);
        Task<List<TopicVm>> GetTopic();
    }
}
