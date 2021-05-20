using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Trainee_Comment
    {
        public int ClassID { set; get; }
        public int ModuleId { set; get; }
        public string TraineeID { set; get; }
        public string Comment { set; get; }

    }
}
