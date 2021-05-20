using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class ClassVm
    {
        public int? ClassID { set; get; }
        public string Name { set; get; }
        public int Capacity { set; get; }
        public DateTime StartTime { set; get; }
        public DateTime EndTime { set; get; }
        public int EnrollmentsCount { set; get; }
    }
}
