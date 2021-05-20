using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Entities
{
    public class Class
    {
        public int ClassID { set; get; }
        public string Name { set; get; }
        public int Capacity { set; get; }
        public DateTime StartTime { set; get; }
        public DateTime EndTime { set; get; }
        public bool IsDeleted { set; get; }

        public List<Assignment> Assignments { set; get; }
        public List<Answer> Answers { set; get; }
        public List<Enrollment> Enrollments { set; get; }
    }
}
