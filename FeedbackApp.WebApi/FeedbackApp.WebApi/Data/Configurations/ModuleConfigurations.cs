using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class ModuleConfigurations : IEntityTypeConfiguration<Module>
    {
        public void Configure(EntityTypeBuilder<Module> builder)
        {
            builder.HasKey(x => x.ModuleID);
            builder.Property(x => x.IsDeleted).HasDefaultValue(false);

            builder.HasOne(x => x.Admin).WithMany(x => x.Modules).HasForeignKey(x => x.AdminID);
            builder.HasOne(x => x.Feedback).WithMany(x => x.Modules).HasForeignKey(x => x.FeedbackID);
        }
    }
}
