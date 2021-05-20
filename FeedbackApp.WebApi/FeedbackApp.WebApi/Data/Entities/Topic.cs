using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Topic
    {
        public int TopicId { set; get; }
        public string Name { set; get; }
        public List<Question> Questions { set; get; }
    }
}
