using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Assignment
    {
        public int ClassId { set; get; }
        public int ModuleID { set; get; }
        public string TrainerID { set; get; }
        public string RegistrationCode { set; get; }
        public Module Module { set; get; }
        public Class Class { set; get; }
        public Trainer Trainer { set; get; }
    }
}
