using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class AssignmentRequest
    {
        public int ClassId { set; get; }
        public int ModuleID { set; get; }
        public string TrainerID { set; get; }
    }
}
