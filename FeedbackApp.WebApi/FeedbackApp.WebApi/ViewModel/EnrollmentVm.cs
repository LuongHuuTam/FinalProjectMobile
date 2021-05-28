using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class EnrollmentVm
    {
        public int ClassId { set; get; }
        public string TraineeId { set; get; }
        public string ClassName { set; get; }
        public string TraineeName { set; get; }
        public ClassVm Class { set; get; }
        public TraineeVm Trainee { set; get; }
    }
}
