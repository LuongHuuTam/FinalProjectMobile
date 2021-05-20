using FeedbackApp.WebApi.Data.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.Configurations
{
    public class Trainee_AssignmentConfiguration : IEntityTypeConfiguration<Trainee_Assignment>
    {
        public void Configure(EntityTypeBuilder<Trainee_Assignment> builder)
        {
            builder.HasKey(x => new { x.RegistrationCode, x.TraineeId });
        }
    }
}
