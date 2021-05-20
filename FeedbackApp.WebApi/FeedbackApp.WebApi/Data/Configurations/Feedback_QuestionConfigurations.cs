using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class Feedback_QuestionConfigurations : IEntityTypeConfiguration<Feedback_Question>
    {
        public void Configure(EntityTypeBuilder<Feedback_Question> builder)
        {
            builder.HasKey(x => new { x.FeedbackId, x.QuestionId });
            builder.HasOne(x => x.Feedback).WithMany(x => x.Feedback_Questions).HasForeignKey(x => x.FeedbackId);
            builder.HasOne(x => x.Question).WithMany(x => x.Feedback_Questions).HasForeignKey(x => x.QuestionId);
        }
    }
}
