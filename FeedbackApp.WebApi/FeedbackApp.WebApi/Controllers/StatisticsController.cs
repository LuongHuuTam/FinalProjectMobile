using FeedbackApp.WebApi.Application.Interface;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class StatisticsController : ControllerBase
    {
        private readonly IStatisticService _statisticService;
        public StatisticsController(IStatisticService statisticService)
        {
            _statisticService = statisticService;
        }

        [HttpGet("{classId}/{moduleId}")]
        public async Task<IActionResult> Statistic(int classId,int moduleId)
        {
            return Ok(await _statisticService.StatisticOfClass(classId, moduleId));
        }
    }
}
