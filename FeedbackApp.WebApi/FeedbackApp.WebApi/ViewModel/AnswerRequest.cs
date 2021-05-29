using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class AnswerRequest
    {
        public int ClassID { set; get; }
        public int ModuleId { set; get; }
        public string TraineeID { set; get; }
        public string Comment { set; get; }

        public List<AnswerChose> Choses {set;get;}
    }
}
