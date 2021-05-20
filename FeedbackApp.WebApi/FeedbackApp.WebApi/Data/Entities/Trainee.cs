using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Trainee
    {
        public string UserName { set; get; }
        public string Name { set; get; }
        public string Email { set; get; }
        public string Password { set; get; }
        public string Phone { set; get; }
        public string Address { set; get; }
        public bool IsActive { set; get; }
        public string ActivationCode { set; get; }
        public string ResetPasswordCode { set; get; }
        public List<Answer> Answers { set; get; }
        public List<Enrollment> Enrollments { set; get; }
    }
}
