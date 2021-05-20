using FeedbackApp.WebApi.Data.Configurations;
using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace FeedbackApp.WebApi.Data.EF
{
    public class FeedbackAppDbContext : DbContext
    {
        public FeedbackAppDbContext(DbContextOptions options):base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfiguration(new AdminConfigurations());
            modelBuilder.ApplyConfiguration(new AnswerConfigurations());
            modelBuilder.ApplyConfiguration(new AssignmentConfigurations());
            modelBuilder.ApplyConfiguration(new ClassConfigurations());
            modelBuilder.ApplyConfiguration(new EnrollmentConfigurations());
            modelBuilder.ApplyConfiguration(new FeedbackConfigurations());
            modelBuilder.ApplyConfiguration(new Feedback_QuestionConfigurations());
            modelBuilder.ApplyConfiguration(new ModuleConfigurations());
            modelBuilder.ApplyConfiguration(new QuestionConfigurations());
            modelBuilder.ApplyConfiguration(new TopicConfigurations());
            modelBuilder.ApplyConfiguration(new TraineeConfigurations());
            modelBuilder.ApplyConfiguration(new Trainee_AssignmentConfiguration());
            modelBuilder.ApplyConfiguration(new Trainee_CommentConfiguration());
            modelBuilder.ApplyConfiguration(new TrainerConfigurations());
            modelBuilder.ApplyConfiguration(new TypeFeedbackConfiguration());

            modelBuilder.Seed();

            //base.OnModelCreating(modelBuilder);
        }

        public DbSet<Admin> Admins { get; set; }
        public DbSet<Answer> Answers { set; get; }
        public DbSet <Assignment> Assignments { set; get; }
        public DbSet<Class> Classes { set; get; }
        public DbSet<Enrollment> Enrollments { set; get; }
        public DbSet<Feedback> Feedbacks { set; get; }
        public DbSet<Feedback_Question> Feedback_Questions { set; get; }
        public DbSet<Module> Modules { set; get; }
        public DbSet<Question> Questions { set; get; }
        public DbSet<Topic> Topics { set; get; }
        public DbSet<Trainee> Trainees { set; get; }
        public DbSet<Trainee_Assignment> Trainee_Assignments { set; get; }
        public DbSet<Trainee_Comment> Trainee_Comments { set; get; }
        public DbSet<Trainer> Trainers { set; get; }
        public DbSet<TypeFeedback> TypeFeedbacks { set; get; }
    }
}
