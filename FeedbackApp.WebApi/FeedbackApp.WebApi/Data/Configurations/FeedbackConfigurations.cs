using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class FeedbackConfigurations : IEntityTypeConfiguration<Feedback>
    {
        public void Configure(EntityTypeBuilder<Feedback> builder)
        {
            builder.HasKey(x => x.FeedbackID);
            builder.Property(x => x.IsDeleted).HasDefaultValue(false);

            builder.HasOne(x => x.Admin).WithMany(x => x.Feedbacks).HasForeignKey(x => x.AdminID);
            builder.HasOne(x => x.TypeFeedback).WithMany(x => x.Feedbacks).HasForeignKey(x => x.TypeFeedbackID);
        }
    }
}
