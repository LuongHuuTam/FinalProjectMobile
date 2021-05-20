using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class AssignmentVm
    {
        public int ClassId { set; get; }
        public int ModuleID { set; get; }
        public string TrainerID { set; get; }
        public string ModuleName { set; get; }
        public string ClassName { set; get; }
        public string TrainerName { set; get; }
        public string RegistrationCode { set; get; }
    }
}
