using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class EnrollmentService : IEnrollmentService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public EnrollmentService(IMapper mapper, FeedbackAppDbContext context)
        {
            _context = context;
            _mapper = mapper;
        }

        public async Task<List<EnrollmentVm>> GetAll(int classId)
        {
            List<Enrollment> data = new List<Enrollment>();
            if (classId == 0)
                data = await _context.Enrollments.Include(x => x.Class).Include(x => x.Trainee).ToListAsync();
            else
                data = await _context.Enrollments.Include(x => x.Class).Include(x => x.Trainee).Where(x => x.ClassId == classId).ToListAsync();
            return _mapper.Map<List<EnrollmentVm>>(data);
        }

        public async Task<EnrollmentVm> GetById(int classId, string traineeId)
        {
            var data = await _context.Enrollments.Where(x => x.ClassId == classId && x.TraineeId == traineeId)
                                .Include(x => x.Class).Include(x => x.Trainee).FirstOrDefaultAsync();
            EnrollmentVm vm = _mapper.Map<EnrollmentVm>(data);
            return vm;
        }

        public async Task<string> Join(JoinRequest request)
        {
            var assignment = await _context.Assignments.Where(x => x.RegistrationCode == request.Code).FirstOrDefaultAsync();
            if (assignment == null)
                return "Invalid Registation Code!!!";

            bool check = await _context.Trainee_Assignments.AnyAsync(x => x.RegistrationCode == request.Code && x.TraineeId == request.TraineeId);
            if (check == true)
                return "You already join this class, please try another!!!";

            Trainee_Assignment ta = new Trainee_Assignment()
            {
                TraineeId = request.TraineeId,
                RegistrationCode = request.Code
            };
            Enrollment enrollment = new Enrollment()
            {
                ClassId = assignment.ClassId,
                TraineeId = request.TraineeId
            };
            try
            {
                _context.Trainee_Assignments.Add(ta);
                _context.Enrollments.Add(enrollment);
                await _context.SaveChangesAsync();
                return null;
            }
            catch
            {
                return "False";
            }            
        }

        public async Task<bool> Update(EnrollmentVm request)
        {
            var data = await _context.Enrollments.Where(x => x.ClassId == request.ClassId && x.TraineeId == request.TraineeId).FirstOrDefaultAsync();
            if (data == null)
                return false;

            data.ClassId = request.ClassId;
            try
            {
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
