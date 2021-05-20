using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class EnrollmentConfigurations : IEntityTypeConfiguration<Enrollment>
    {
        public void Configure(EntityTypeBuilder<Enrollment> builder)
        {
            builder.HasKey(x => new { x.ClassId, x.TraineeId });
            builder.HasOne(x => x.Trainee).WithMany(x => x.Enrollments).HasForeignKey(x => x.TraineeId);
            builder.HasOne(x => x.Class).WithMany(x => x.Enrollments).HasForeignKey(x => x.ClassId);
        }
    }
}
