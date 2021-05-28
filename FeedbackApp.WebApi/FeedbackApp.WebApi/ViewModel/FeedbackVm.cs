using FeedbackApp.WebApi.Data.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class FeedbackVm
    {
        public int? FeedbackID { set; get; }
        public string Title { set; get; }
        public string AdminID { set; get; }
        public int TypeFeedbackID { set; get; }
        public string TypeFeedbackName { set; get; }
        public List<QuestionVm> Questions { set; get; } = new List<QuestionVm>();
        
    }
}
