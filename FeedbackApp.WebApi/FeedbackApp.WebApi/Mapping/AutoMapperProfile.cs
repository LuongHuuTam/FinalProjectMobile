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
            CreateMap<Enrollment, ClassVm>();

            CreateMap<Topic, TopicVm>();

            CreateMap<Trainee, TraineeVm>();
            CreateMap<Trainee, UserVm>();
            CreateMap<Trainer, UserVm>();

            CreateMap<Question, QuestionVm>();
            CreateMap<QuestionVm, Question>();


            CreateMap<Feedback_Question, QuestionVm>();
            CreateMap<QuestionVm, Feedback_Question>();

            CreateMap<Enrollment, EnrollmentVm>();
            CreateMap<AnswerRequest, Trainee_Comment>();
            CreateMap<AnswerRequest, Answer>();

            CreateMap<Answer, StatisticVm>();
        }
    }
}
