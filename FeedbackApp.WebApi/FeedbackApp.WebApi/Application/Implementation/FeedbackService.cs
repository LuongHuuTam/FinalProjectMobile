using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class FeedbackService : IFeedbackService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public FeedbackService(FeedbackAppDbContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        public async Task<bool> Add(FeedbackVm request)
        {
            Feedback feedback = _mapper.Map<Feedback>(request);
            List<Feedback_Question> feedback_Questions = _mapper.Map<List<Feedback_Question>>(request.Questions);
            feedback.Feedback_Questions = feedback_Questions;
            try
            {
                _context.Feedbacks.Add(feedback);
                await _context.SaveChangesAsync();                
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> Delete(int feedbackId)
        {
            Feedback feedback = await _context.Feedbacks.Where(x => x.FeedbackID == feedbackId).FirstOrDefaultAsync();
            if (feedback == null)
                return false;

            feedback.IsDeleted = true;
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

        public async Task<List<FeedbackVm>> GetAll()
        {
            List<FeedbackVm> res = new List<FeedbackVm>();
            var data = await _context.Feedbacks.Where(x => x.IsDeleted == false).Include(x => x.TypeFeedback).ToListAsync();
            res = _mapper.Map<List<FeedbackVm>>(data);
            return res;
        }

        public async Task<FeedbackVm> GetById(int feedbackId)
        {            
            var question = (from fb in _context.Feedbacks
                            join fbq in _context.Feedback_Questions on fb.FeedbackID equals fbq.FeedbackId
                            join q in _context.Questions on fbq.QuestionId equals q.QuestionID
                            join tp in _context.Topics on q.TopicID equals tp.TopicId
                            where fb.FeedbackID == feedbackId
                            select new { q, tp }).ToList();

            var dataTemp = await _context.Feedbacks.FindAsync(feedbackId);
            var feedback = _mapper.Map<FeedbackVm>(dataTemp);
            if (question.Count > 0)
            {
                foreach (var temp in question)
                {
                    QuestionVm vm = new QuestionVm()
                    {
                        TopicId = temp.tp.TopicId,
                        TopicName = temp.tp.Name,
                        Content = temp.q.Content,
                        QuestionID = temp.q.QuestionID
                    };
                    feedback.Questions.Add(vm);
                }
            }
            return feedback;
        }

        public async Task<List<TypeFeedback>> GetTypeFeedback()
        {
            return await _context.TypeFeedbacks.Where(x => x.IsDeleted == false).ToListAsync();
        }

        public async Task<bool> Update(int feedbackId, FeedbackVm request)
        {
            Feedback feedback = await _context.Feedbacks.Where(x => x.FeedbackID == feedbackId).Include(x => x.Feedback_Questions).FirstOrDefaultAsync();
            if (feedback == null)
                return false;

            List<Feedback_Question> feedback_Questions = _mapper.Map<List<Feedback_Question>>(request.Questions);
            foreach(var item in feedback.Feedback_Questions)
            {
                _context.Feedback_Questions.Remove(item);
            }
            feedback.AdminID = request.AdminID;
            feedback.Title = request.Title;
            feedback.TypeFeedbackID = request.TypeFeedbackID;
            feedback.Feedback_Questions = feedback_Questions;

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
    }
}
