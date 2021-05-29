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

        [HttpGet("{feedbackId}")]
        public async Task<IActionResult> GetById(int feedbackId)
        {
            return Ok(await _feedbackService.GetById(feedbackId));
        }

        [HttpGet("typefeedback")]
        public async Task<IActionResult> GetTypeFeedback()
        {
            return Ok(await _feedbackService.GetTypeFeedback());
        }

        [HttpPut("{feedbackId}")]
        public async Task<IActionResult> Update(int feedbackId, [FromBody] FeedbackVm request)
        {
            if (await _feedbackService.Update(feedbackId, request))
                return Ok();
            return BadRequest("False");
        }

        [HttpDelete("{feedbackId}")]
        public async Task<IActionResult> Delete(int feedbackId)
        {
            if (await _feedbackService.Delete(feedbackId))
                return Ok();
            return BadRequest("False");
        }

        [HttpGet("dofeedback/{traineeId}")]
        public async Task<IActionResult> GetDoFeedback(string traineeId)
        {
            return Ok(await _feedbackService.GetDoFeedBack(traineeId));
        }

        [HttpPost("answer")]
        public async Task<IActionResult> AddAnswer([FromBody] AnswerRequest request)
        {
            if (await _feedbackService.AddAnswer(request))
                return Ok();
            return BadRequest("False");
        }
    }
}
