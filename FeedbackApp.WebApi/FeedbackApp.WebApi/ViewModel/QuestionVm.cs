using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class QuestionVm
    {
        public int TopicId { set; get; }
        public string TopicName { set; get; }
        public int QuestionID { set; get; }
        public string Content { set; get; }
    }
}
