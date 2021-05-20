using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class ClassService : IClassService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IMapper _mapper;
        public ClassService(FeedbackAppDbContext context, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
        }

        public async Task<bool> AddClass(ClassVm request)
        {
            Class cl = _mapper.Map<Class>(request);
            try
            {
                _context.Classes.Add(cl);
                await _context.SaveChangesAsync();
                return true;
            }
            catch
            {
                return false;
            }
        }

        public async Task<bool> DeleteClass(int classId)
        {
            Class cl = await _context.Classes.FindAsync(classId);
            cl.IsDeleted = true;
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

        public async Task<bool> EditClass(int classId, ClassVm request)
        {
            var cl = await _context.Classes.FindAsync(classId);

            cl.Name = request.Name;
            cl.Capacity = request.Capacity;
            cl.StartTime = request.StartTime;
            cl.EndTime = request.EndTime;

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

        public async Task<List<ClassVm>> GetAll()
        {
            var cls = await _context.Classes.Where(x => x.IsDeleted == false).Include(x => x.Enrollments).ToListAsync();
            return _mapper.Map<List<ClassVm>>(cls);
        }

        public async Task<List<TraineeVm>> TraineeInClass(int classId)
        {
            var trainees = await _context.Enrollments.Where(x => x.ClassId == classId).Include(x => x.Trainee).ToListAsync();
            return _mapper.Map<List<TraineeVm>>(trainees);
        }

        public async Task<List<ClassVm>> ClassOfTrainee(string username)
        {
            List<ClassVm> res = new List<ClassVm>();
            var cls = await _context.Enrollments.Where(x => x.TraineeId == username).Include(x => x.Class).ToListAsync();
            foreach (var temp in cls)
            {
                if (temp.Class.IsDeleted == false)
                {
                    ClassVm cl = new ClassVm()
                    {
                        ClassID = temp.Class.ClassID,
                        Capacity = temp.Class.Capacity,
                        Name = temp.Class.Name,
                        StartTime = temp.Class.StartTime,
                        EndTime = temp.Class.EndTime,
                        EnrollmentsCount = _context.Enrollments.Count(x => x.ClassId == temp.Class.ClassID)
                    };
                    res.Add(cl);
                }
            }
            return res;
        }

        public async Task<List<ClassVm>> ClassOfTrainer(string username)
        {
            List<ClassVm> res = new List<ClassVm>();
            var cls = await _context.Assignments.Where(x => x.TrainerID == username).Include(x => x.Class).ToListAsync();
            foreach (var temp in cls)
            {
                if (temp.Class.IsDeleted == false)
                {
                    ClassVm cl = new ClassVm()
                    {
                        ClassID = temp.Class.ClassID,
                        Capacity = temp.Class.Capacity,
                        Name = temp.Class.Name,
                        StartTime = temp.Class.StartTime,
                        EndTime = temp.Class.EndTime,
                        EnrollmentsCount = _context.Enrollments.Count(x => x.ClassId == temp.Class.ClassID)
                    };
                    res.Add(cl);
                }
            }
            return res;
        }

        public async Task<ClassVm> GetById(int classId)
        {
            var cl = await _context.Classes.Where(x => x.IsDeleted == false && x.ClassID == classId).Include(x => x.Enrollments).FirstOrDefaultAsync();
            return _mapper.Map<ClassVm>(cl);
        }
    }
}
