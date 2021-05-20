using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class Trainee_CommentConfiguration : IEntityTypeConfiguration<Trainee_Comment>
    {
        public void Configure(EntityTypeBuilder<Trainee_Comment> builder)
        {
            builder.HasKey(x => new { x.ClassID, x.ModuleId, x.TraineeID });
        }
    }
}
