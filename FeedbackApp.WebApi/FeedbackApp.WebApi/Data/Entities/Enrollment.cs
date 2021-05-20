using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Enrollment
    {
        public int ClassId { set; get; }
        public string TraineeId { set; get; }
        public Class Class { set; get; }
        public Trainee Trainee { set; get; }
    }
}
