using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    //[Authorize]
    public class ClassesController : ControllerBase
    {
        private readonly IClassService _classService;
        public ClassesController(IClassService classService)
        {
            _classService = classService;
        }

        [HttpPost]
        public async Task<IActionResult> AddClass([FromBody] ClassVm request)
        {
            if (await _classService.AddClass(request))
                return Ok("Successful");
            return BadRequest("Class is exist");
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            return Ok(await _classService.GetAll());
        }
        [HttpGet("{classId}")]
        public async Task<IActionResult> GetById(int classId)
        {
            return Ok(await _classService.GetById(classId));
        }

        [HttpGet("trainee/{classId}")]
        public async Task<IActionResult> GetTraineeInClass(int classId)
        {
            return Ok(await _classService.TraineeInClass(classId));
        }

        [HttpGet("traineeclass/{username}")]
        public async Task<IActionResult> GetClassOfTrainee(string username)
        {
            return Ok(await _classService.ClassOfTrainee(username));
        }

        [HttpGet("trainerclass/{username}")]
        public async Task<IActionResult> GetClassOfTrainer(string username)
        {
            return Ok(await _classService.ClassOfTrainer(username));
        }


        [HttpPut("{classId}")]
        public async Task<IActionResult> EditClass(int classId, [FromBody] ClassVm request)
        {
            if (await _classService.EditClass(classId, request))
                return Ok("Successful");
            return BadRequest("Class is exist");
        }

        [HttpDelete("{classId}")]
        public async Task<IActionResult> DeleteClass(int classId)
        {
            if (await _classService.DeleteClass(classId))
                return Ok("Successful");
            return BadRequest("False");
        }

        
    }
}
