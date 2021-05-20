using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Question
    {
        public int QuestionID { set; get; }
        public int TopicID { set; get; }
        public string Content { set; get; }
        public bool IsDeleted { set; get; }

        public Topic Topic { set; get; }
        public List<Feedback_Question> Feedback_Questions { set; get; }
        public List<Answer> Answers { set; get; }
    }
}
