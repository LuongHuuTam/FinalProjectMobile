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
    public class QuestionService : IQuestionService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public QuestionService(FeedbackAppDbContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        public async Task<bool> AddQuestion(QuestionVm request)
        {
            Question question = _mapper.Map<Question>(request);
            try
            {
                _context.Questions.Add(question);
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> DeleteQuestion(int questionId)
        {
            var question = await _context.Questions.FindAsync(questionId);
            question.IsDeleted = true;
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

        public async Task<bool> EditQuestion(int questionId, QuestionVm request)
        {
            Question question = await _context.Questions.FindAsync(request.QuestionID);
            question.Content = request.Content;
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

        public async Task<List<QuestionVm>> GetAll(int topicId)
        {
            List<Question> questions = new List<Question>();
            if (topicId == 0)
            {
                questions = await _context.Questions.Where(x => x.IsDeleted == false).Include(x => x.Topic).ToListAsync();
            }
            else
            {
                questions = await _context.Questions.Where(x => x.IsDeleted == false && x.TopicID == topicId).Include(x => x.Topic).ToListAsync();
            }
            return _mapper.Map<List<QuestionVm>>(questions);
        }

        public async Task<QuestionVm> GetById(int questionId)
        {
            var question = await _context.Questions.Where(x => x.QuestionID == questionId && x.IsDeleted == false).Include(x => x.Topic).FirstOrDefaultAsync();
            return _mapper.Map<QuestionVm>(question);
        }

        public async Task<List<TopicVm>> GetTopic()
        {
            return _mapper.Map<List<TopicVm>>(await _context.Topics.ToListAsync());
        }
    }
}
