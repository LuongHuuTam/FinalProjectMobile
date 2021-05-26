using AutoMapper;
using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Mapping
{
    public class AutoMapperProfile : Profile
    {
        public AutoMapperProfile()
        {
            CreateMap<FeedbackVm, Feedback>();
            CreateMap<Feedback, FeedbackVm>();

            CreateMap<ModuleVm, Module>();
            CreateMap<Module, ModuleVm>();

            CreateMap<ClassVm, Class>();
            CreateMap<Class, ClassVm>();

            CreateMap<Assignment, AssignmentVm>();
            CreateMap<AssignmentRequest, Assignment>();

            CreateMap<Enrollment, TraineeVm>();

            CreateMap<Topic, TopicVm>();

            CreateMap<Trainee, UserVm>();
            CreateMap<Trainer, UserVm>();

            CreateMap<Question, QuestionVm>();
            CreateMap<QuestionVm, Question>();

            CreateMap<Feedback_Question, QuestionVm>(); 
        }
    }
}
