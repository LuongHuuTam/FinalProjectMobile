using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Module
    {
        public int ModuleID { set; get; }
        public string AdminID { set; get; }
        public string Name { set; get; }
        public DateTime StartTime { set; get; }
        public DateTime EndTime { set; get; }
        public bool IsDeleted { set; get; }
        public DateTime FeedbackStartTime { set; get; }
        public DateTime FeedbackEndTime { set; get; }
        public int? FeedbackID { set; get; }

        public Admin Admin { set; get; }
        public Feedback Feedback { set; get; }
        public List<Assignment> Assignments { set; get; }
        public List<Answer> Answers { set; get; }
    }
}
