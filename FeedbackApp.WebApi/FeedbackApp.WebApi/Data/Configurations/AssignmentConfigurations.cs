using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class AssignmentConfigurations : IEntityTypeConfiguration<Assignment>
    {
        public void Configure(EntityTypeBuilder<Assignment> builder)
        {
            builder.HasKey(x => new { x.ClassId, x.ModuleID, x.TrainerID });            

            builder.HasOne(x => x.Module).WithMany(x => x.Assignments).HasForeignKey(x => x.ModuleID);
            builder.HasOne(x => x.Class).WithMany(x => x.Assignments).HasForeignKey(x => x.ClassId);
            builder.HasOne(x => x.Trainer).WithMany(x => x.Assignments).HasForeignKey(x => x.TrainerID);
        }
    }
}
