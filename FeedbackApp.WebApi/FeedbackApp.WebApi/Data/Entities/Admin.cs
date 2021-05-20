using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Admin
    {
        public string UserName { set; get; }
        public string Name { set; get; }
        public string Email { set; get; }
        public string Password { set; get; }

        public List<Feedback> Feedbacks { set; get; }
        public List<Module> Modules { set; get; }
    }
}
