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
    public class EnrollmentsController : ControllerBase
    {
        private readonly IEnrollmentService _enrollmentService;
        public EnrollmentsController(IEnrollmentService enrollmentService)
        {
            _enrollmentService = enrollmentService;
        }

        [HttpGet]
        public async Task<IActionResult> GetAll(int classId = 0)
        {
            return Ok(await _enrollmentService.GetAll(classId));
        }

        [HttpGet("{classId}/{traineeId}")]
        public async Task<IActionResult> GetById(int classId, string traineeId)
        {
            return Ok(await _enrollmentService.GetById(classId, traineeId));
        }

        [HttpPut]
        public async Task<IActionResult> Update([FromBody] EnrollmentVm request)
        {
            if (await _enrollmentService.Update(request))
                return Ok("Successfull");
            return BadRequest("Trainee is in class");
        }

        [HttpPost]
        public async Task<IActionResult> JoinClass([FromBody] JoinRequest request)
        {
            string response = await _enrollmentService.Join(request);
            if (string.IsNullOrEmpty(response))
                return Ok();
            return BadRequest(response);
        }
    }
}
