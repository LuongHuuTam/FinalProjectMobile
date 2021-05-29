using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class StatisticService : IStatisticService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public StatisticService(FeedbackAppDbContext context, IMapper mapper)
        {
            _mapper = mapper;
            _context = context;
        }

        public async Task<List<StatisticVm>> StatisticOfClass(int classId, int moduleId)
        {
            var data = await _context.Answers.Where(x => x.ClassId == classId && x.ModuleID == moduleId)
                .Include(x => x.Question).ToListAsync();

            List<StatisticVm> statisticVms = _mapper.Map<List<StatisticVm>>(data);
            foreach(var item in statisticVms)
            {
                item.Agree = data.Where(x => x.QuestionID == item.QuestionID && x.Value == "Agree").Count();
                item.Disagree = data.Where(x => x.QuestionID == item.QuestionID && x.Value == "Disagree").Count();
                item.UnDecided = data.Where(x => x.QuestionID == item.QuestionID && x.Value == "UnDecided").Count();
                item.StronglyAgree = data.Where(x => x.QuestionID == item.QuestionID && x.Value == "Strongly Agree").Count();
                item.StronglyDisagree = data.Where(x => x.QuestionID == item.QuestionID && x.Value == "Strongly Disagree").Count();
            }
            return statisticVms;
        }        
    }
}
