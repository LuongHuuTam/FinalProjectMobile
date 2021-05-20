using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private readonly IUserService _userService;
        public UsersController(IUserService userService)
        {
            _userService = userService;
        }


        [HttpPost("authenticate")]
        public async Task<IActionResult> Authenticate([FromBody] LoginRequest request)
        {
            LoginResponse response = await _userService.Authenticate(request);
            if (response == null)
                return BadRequest("Login fail");
            return Ok(response);
        }
        [HttpGet("trainer")]
        public async Task<IActionResult> ListTrainer()
        {
            return Ok(await _userService.ListTrainer());
        }

        [HttpGet("trainee")]
        public async Task<IActionResult> ListTrainee()
        {
            return Ok(await _userService.ListTrainee());
        }
    }
}
