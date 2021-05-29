using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class StatisticVm
    {
        public int QuestionTopicID { set; get; }
        public int QuestionID { set; get; }
        public string QuestionContent { set; get; }
        public int StronglyDisagree { set; get; }
        public int Disagree { set; get; }
        public int UnDecided { set; get; }
        public int Agree { set; get; }
        public int StronglyAgree { set; get; }
    }
}
