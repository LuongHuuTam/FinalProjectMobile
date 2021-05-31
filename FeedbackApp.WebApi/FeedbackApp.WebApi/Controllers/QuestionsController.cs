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
    public class QuestionsController : ControllerBase
    {
        private readonly IQuestionService _questionService;
        public QuestionsController(IQuestionService questionService)
        {
            _questionService = questionService;
        }

        [HttpGet("topic")]
        public async Task<IActionResult> GetTopic()
        {
            return Ok(await _questionService.GetTopic());
        }

        [HttpGet]
        public async Task<IActionResult> GetQuestion(int topicId)
        {
            return Ok(await _questionService.GetAll(topicId));
        }

        [HttpGet("{questionId}")]
        public async Task<IActionResult> GetQuestionById(int questionId = 0)
        {
            return Ok(await _questionService.GetById(questionId));
        }

        [HttpPost]
        public async Task<IActionResult> AddQuestion([FromBody] QuestionVm request)
        {
            if (await _questionService.AddQuestion(request))
                return Ok("Successfull");
            return BadRequest("Question is exist");
        }

        [HttpPut("{questionId}")]
        public async Task<IActionResult> EditQuestion(int questionId, [FromBody] QuestionVm request)
        {
            if (await _questionService.EditQuestion(questionId, request))
                return Ok("Successfull");
            return BadRequest("Question is exist");
        }

        [HttpDelete("{questionId}")]
        public async Task<IActionResult> DeleteQuestion(int questionId)
        {
            if (await _questionService.DeleteQuestion(questionId))
                return Ok("Successfull");
            return BadRequest("False");
        }
    }
}
