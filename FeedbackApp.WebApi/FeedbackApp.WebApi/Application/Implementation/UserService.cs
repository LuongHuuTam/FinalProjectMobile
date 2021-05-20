using AutoMapper;
using FeedbackApp.WebApi.Application.Interface;
using FeedbackApp.WebApi.Data.EF;
using FeedbackApp.WebApi.Utilities;
using FeedbackApp.WebApi.ViewModel;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Threading.Tasks;

namespace FeedbackApp.WebApi.Application.Implementation
{
    public class UserService : IUserService
    {
        private readonly FeedbackAppDbContext _context;
        private readonly IConfiguration _configuration;
        private readonly IMapper _mapper;
        public UserService(FeedbackAppDbContext context, IConfiguration configuration, IMapper mapper)
        {
            _context = context;
            _mapper = mapper;
            _configuration = configuration;
        }

        public async Task<LoginResponse> Authenticate(LoginRequest request)
        {
            LoginResponse response = new LoginResponse();

            switch (request.Role)
            {
                case 1:
                    var user = await _context.Admins.FindAsync(request.Username);
                    if (user != null && Encryptor.MD5Hash(request.Password) == user.Password)
                    {
                        response.Token = await CreateToken(user.UserName, user.Name);
                        response.Role = "Admin";
                        response.Username = request.Username;
                    }
                    break;
                case 2:
                    var user1 = await _context.Trainers.FindAsync(request.Username);
                    if (user1 != null && Encryptor.MD5Hash(request.Password) == user1.Password)
                    {
                        response.Token = await CreateToken(user1.UserName, user1.Name);
                        response.Role = "Trainer";
                        response.Username = request.Username;
                    }
                    break;
                case 3:
                    var user3 = await _context.Trainees.FindAsync(request.Username);
                    if (user3 != null && Encryptor.MD5Hash(request.Password) == user3.Password)
                    {
                        response.Token = await CreateToken(user3.UserName, user3.Name);
                        response.Role = "Trainee";
                        response.Username = request.Username;
                    }
                    break;
            }

            if (string.IsNullOrEmpty(response.Token))
                return null;
            return response;
        }

        public async Task<List<UserVm>> ListTrainee()
        {
            var data = await _context.Trainees.ToListAsync();
            return _mapper.Map<List<UserVm>>(data);
        }

        public async Task<List<UserVm>> ListTrainer()
        {
            var data = await _context.Trainers.ToListAsync();
            return _mapper.Map<List<UserVm>>(data);
        }

        private async Task<string> CreateToken(string username, string name)
        {
            var claims = new List<Claim>
            {
                new Claim(ClaimTypes.Name,username),
                new Claim(ClaimTypes.GivenName,name)
            };
            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Tokens:Key"]));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);
            var token = new JwtSecurityToken(_configuration["Tokens:Issuer"],
                _configuration["Tokens:Issuer"],
                claims,
                expires: DateTime.Now.AddHours(3),
                signingCredentials: creds);
            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}
