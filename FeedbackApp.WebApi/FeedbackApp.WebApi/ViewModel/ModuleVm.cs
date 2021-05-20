using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class ModuleVm
    {
        public int? ModuleID { set; get; }
        public string AdminID { set; get; }
        public string Name { set; get; }
        public DateTime StartTime { set; get; }
        public DateTime EndTime { set; get; }
        public DateTime FeedbackStartTime { set; get; }
        public DateTime FeedbackEndTime { set; get; }
        public int? FeedbackID { set; get; }
        public string FeedbackTitle { set; get; }
    }
}
