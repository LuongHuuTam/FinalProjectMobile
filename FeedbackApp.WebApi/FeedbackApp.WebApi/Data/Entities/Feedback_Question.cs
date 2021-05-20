using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Feedback_Question
    {
        public int FeedbackId { set; get; }
        public int QuestionId { set; get; }

        public Feedback Feedback { set; get; }
        public Question Question { set; get; }
    }
}
