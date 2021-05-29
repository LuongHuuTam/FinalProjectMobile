using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Answer
    {
        public int ClassId { set; get; }
        public int ModuleID { set; get; }
        public string TraineeID { set; get; }
        public int QuestionID { set; get; }
        public string Value { set; get; }

        public Module Module { set; get; }
        public Class Class { set; get; }
        public Question Question { set; get; }
        public Trainee Trainee { set; get; }
    }
}
