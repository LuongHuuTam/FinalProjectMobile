using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Interface
{
    public interface IStatisticService
    {
        Task<List<StatisticVm>> StatisticOfClass(int classId, int moduleId);
    }
}
