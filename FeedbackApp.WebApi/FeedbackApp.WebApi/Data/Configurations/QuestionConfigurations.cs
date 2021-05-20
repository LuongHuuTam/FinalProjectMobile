using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class QuestionConfigurations : IEntityTypeConfiguration<Question>
    {
        public void Configure(EntityTypeBuilder<Question> builder)
        {
            builder.HasKey(x => x.QuestionID);
            builder.Property(x => x.IsDeleted).HasDefaultValue(false);
            builder.HasOne(x => x.Topic).WithMany(x => x.Questions).HasForeignKey(x => x.TopicID);
        }
    }
}
