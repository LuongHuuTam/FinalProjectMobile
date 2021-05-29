using FeedbackApp.WebApi.Data.Entities;
using FeedbackApp.WebApi.Utilities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.EF
{
    public static class ModelBuilderExtensions
    {
        public static void Seed(this ModelBuilder modelBuilder)
        {
            var password = Encryptor.MD5Hash("test123");
            modelBuilder.Entity<Admin>().HasData(
                new Admin() { UserName = "admin", Email = "admin@gmail.com", Name = "admin", Password = password }
            );
            modelBuilder.Entity<Trainee>().HasData(
                new Trainee() { UserName = "trainee", Email = "admin@gmail.com", Name = "admin", Password = password, IsActive = true }
            );
            modelBuilder.Entity<Trainer>().HasData(
                new Trainer() { UserName = "trainer", Email = "admin@gmail.com", Name = "admin", Password = password, IsActive = true }
            );

            modelBuilder.Entity<Topic>().HasData(
                new Topic() { TopicId = 1, Name = "Training program & content (nội dung đào tạo)" },
                new Topic() { TopicId = 2, Name = "Trainer/Coach" },
                new Topic() { TopicId = 3, Name = "Cource organization (tổ chức khóa học)" },
                new Topic() { TopicId = 4, Name = "Other" }
                );

            modelBuilder.Entity<TypeFeedback>().HasData(
                new TypeFeedback() { TypeId = 1, Name = "Online" },
                new TypeFeedback() { TypeId = 2, Name = "Offline" }
                );
        }
    }
}
