using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.ViewModel
{
    public class LoginResponse
    {
        public string Token { set; get; }
        public string Username { set; get; }
        public string Role { set; get; }
    }
}
