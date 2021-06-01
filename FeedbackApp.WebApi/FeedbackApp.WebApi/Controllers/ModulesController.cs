using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    //[Authorize]
    public class ModulesController : ControllerBase
    {
        private readonly IModuleService _moduleService;
        public ModulesController(IModuleService moduleService)
        {
            _moduleService = moduleService;
        }

        [HttpPost]
        public async Task<IActionResult> AddModule([FromBody] ModuleVm request)
        {
            if (await _moduleService.AddModule(request))
                return Ok("Successfull");
            return BadRequest("Mudule is exist");
        }


        [HttpPut("{moduleId}")]
        public async Task<IActionResult> EditModule(int moduleId, [FromBody] ModuleVm request)
        {
            if (await _moduleService.EditModule(moduleId, request))
                return Ok("Successfull");
            return BadRequest("Mudule is exist");
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            return Ok(await _moduleService.GetAll());
        }

        [HttpGet("{moduleId}")]
        public async Task<IActionResult> GetById(int moduleId)
        {
            return Ok(await _moduleService.GetById(moduleId));
        }

        [HttpDelete("{moduleId}")]
        public async Task<IActionResult> Delete(int moduleId)
        {
            if (await _moduleService.DeleteModule(moduleId))
                return Ok("Successfull");
            return BadRequest("False");
        }
    }
}
