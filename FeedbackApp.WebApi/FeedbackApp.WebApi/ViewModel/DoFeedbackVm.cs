using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class DoFeedbackVm
    {
        public string FeedbackTitle { set; get; }
        public int ClassID { set; get; }
        public string ClassName { set; get; }
        public int ModuleId { set; get; }
        public string ModuleName { set; get; }
        public DateTime EndTime { set; get; }
        public bool Status { set; get; }

    }
}
