using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.Entities;
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
    public class AssignmentsController : ControllerBase
    {
        private readonly IAssignmentService _assignmentService;
        private readonly IMapper _mapper;
        public AssignmentsController(IAssignmentService assignmentService, IMapper mapper)
        {
            _mapper = mapper;
            _assignmentService = assignmentService;
        }

        [HttpGet]
        public async Task<IActionResult> GetAll(string search = "")
        {
            return Ok(await _assignmentService.GetAll(search));
        }
        [HttpGet("{trainerId}")]
        public async Task<IActionResult> GetAssignment(string trainerId, string search = "")
        {
            return Ok(await _assignmentService.GetAssignmentOfTrainer(trainerId, search));
        }
        [HttpGet("{classId}/{moduleId}/{trainerId}")]
        public async Task<IActionResult> GetById(int classId, int moduleId, string trainerId)
        {
            AssignmentRequest request = new AssignmentRequest()
            {
                ClassId = classId,
                ModuleID = moduleId,
                TrainerID = trainerId
            };
            return Ok(await _assignmentService.GetById(request));
        }

        [HttpPost]
        public async Task<IActionResult> AddAssignment([FromBody] AssignmentRequest request)
        {
            Assignment temp = _mapper.Map<Assignment>(request);
            if (await _assignmentService.AddAssignment(temp))
            {
                return Ok("Successful");
            }
            return BadRequest("Assignment Is Exist");
        }

        [HttpPost("delete")]
        public async Task<IActionResult> DeleteAssignment([FromBody] AssignmentRequest request)
        {
            Assignment temp = _mapper.Map<Assignment>(request);
            if (await _assignmentService.DeleteAssignment(temp))
            {
                return Ok("Successful");
            }
            return BadRequest("False");
        }

        [HttpPut]
        public async Task<IActionResult> EditAssignment([FromBody] AssignmentRequest request)
        {
            Assignment temp = _mapper.Map<Assignment>(request);
            if (await _assignmentService.EditAssignment(temp))
            {
                return Ok("Successful");
            }
            return BadRequest("Assignment Is Exist");
        }
    }
}
