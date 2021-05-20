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
    public class FeedbacksController : ControllerBase
    {
        private readonly IFeedbackService _feedbackService;
        public FeedbacksController(IFeedbackService feedbackService)
        {
            _feedbackService = feedbackService;
        }

        [HttpPost]
        public async Task<IActionResult> Add([FromBody] FeedbackVm request)
        {
            if (await _feedbackService.Add(request))
                return Ok();
            return BadRequest("False");
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            return Ok(await _feedbackService.GetAll());
        }
    }
}
