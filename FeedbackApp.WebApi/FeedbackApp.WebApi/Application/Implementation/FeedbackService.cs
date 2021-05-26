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
            try
            {
                _context.Feedbacks.Add(feedback);
                await _context.SaveChangesAsync();
                foreach(var question in request.Questions)
                {
                    Feedback_Question feedback_Question = new Feedback_Question()
                    {
                        FeedbackId = feedback.FeedbackID,
                        QuestionId = question.QuestionID
                    };
                    _context.Feedback_Questions.Add(feedback_Question);
                }
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
            var data = await _context.Feedbacks.Where(x => x.IsDeleted == false).Include(x=>x.TypeFeedback).Include(x=>x.Feedback_Questions).ToListAsync();
            res = _mapper.Map<List<FeedbackVm>>(data);

            //if (data != null)
            //{
            //    for(int i=0;i<data.Count();i++)
            //    {
            //        List<QuestionVm>
            //    }
            //}
            return res;
        }

        public async Task<FeedbackVm> GetById(int feedbackId)
        {
            var fb = await _context.Feedbacks.FindAsync(feedbackId);
            return _mapper.Map<FeedbackVm>(fb);
        }
    }
}
