using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Utilities
{
    public class RegistationCode
    {
        public static string GetCode(int classId, int moduleId)
        {
            var timestamp = DateTime.Now.ToString("HHmmssffff");
            return "CL" + classId.ToString() + "M" + moduleId.ToString() + "T" + timestamp;
        }
    }
}
