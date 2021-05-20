using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class TypeFeedbackConfiguration : IEntityTypeConfiguration<TypeFeedback>
    {
        public void Configure(EntityTypeBuilder<TypeFeedback> builder)
        {
            builder.HasKey(x => x.TypeId);
            builder.Property(x => x.IsDeleted).HasDefaultValue(false);
        }
    }
}
