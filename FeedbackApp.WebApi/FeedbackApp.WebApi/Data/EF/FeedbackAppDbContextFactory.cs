using FeedbackApp.WebApi.Utilities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Data.EF
{
    public class FeedbackAppDbContextFactory : IDesignTimeDbContextFactory<FeedbackAppDbContext>
    {
        public FeedbackAppDbContext CreateDbContext(string[] args)
        {
            IConfigurationRoot configuration = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json")
                .Build();

            var connectionstring = configuration.GetConnectionString(WConstants.MainConnectionString);

            var optionsBuilder = new DbContextOptionsBuilder<FeedbackAppDbContext>();
            optionsBuilder.UseSqlServer(connectionstring);

            return new FeedbackAppDbContext(optionsBuilder.Options);
        }
    }
}
