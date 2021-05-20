using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Feedback
    {
        public int FeedbackID { set; get; }
        public string Title { set; get; }
        public string AdminID { set; get; }
        public bool IsDeleted { set; get; }
        public int TypeFeedbackID { set; get; }

        public Admin Admin { set; get; }
        public TypeFeedback TypeFeedback { set; get; }
        public List<Feedback_Question> Feedback_Questions { set; get; }
        public List<Module> Modules { set; get; }
    }
}
