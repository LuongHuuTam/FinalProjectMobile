using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class TypeFeedback
    {
        public int TypeId { set; get; }
        public string Name { set; get; }
        public bool IsDeleted { set; get; }

        public List<Feedback> Feedbacks { set; get; }
    }
}
