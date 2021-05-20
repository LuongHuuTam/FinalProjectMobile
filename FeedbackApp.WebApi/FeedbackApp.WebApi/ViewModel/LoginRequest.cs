using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class LoginRequest
    {
        public string Username { set; get; }
        public string Password { set; get; }
        public int Role { set; get; }
    }
}
