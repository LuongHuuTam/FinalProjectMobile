using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class AnswerConfigurations : IEntityTypeConfiguration<Answer>
    {
        public void Configure(EntityTypeBuilder<Answer> builder)
        {
            builder.HasKey(x => new { x.ClassId, x.ModuleID, x.QuestionID, x.TraineeID });

            builder.HasOne(x => x.Module).WithMany(x => x.Answers).HasForeignKey(x => x.ModuleID);
            builder.HasOne(x => x.Question).WithMany(x => x.Answers).HasForeignKey(x => x.QuestionID);
            builder.HasOne(x => x.Class).WithMany(x => x.Answers).HasForeignKey(x => x.ClassId);
            builder.HasOne(x => x.Trainee).WithMany(x => x.Answers).HasForeignKey(x => x.TraineeID);
        }
    }
}
