using System.Security.Cryptography;
using System.Text;

// Controllers/UsersController.cs
using Microsoft.AspNetCore.Mvc;
using DoaMais.Models;
using Npgsql;
using System.Threading.Tasks;
using System.Collections.Generic;
using Microsoft.Extensions.Configuration;
using System;

namespace DoaMais.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class UsersController : ControllerBase
    {
        private readonly string _connectionString;

        public UsersController(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
            if (string.IsNullOrEmpty(_connectionString))
            {
                throw new ArgumentNullException(_connectionString);
            }
        }

        // POST: /Users/Login
        [HttpPost("Login")]
        public async Task<ActionResult<User>> Login(UserLogin userLogin)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("SELECT * FROM doamais.users WHERE username = @username", conn))
                {
                    cmd.Parameters.AddWithValue("username", userLogin.Username);
                    var reader = await cmd.ExecuteReaderAsync();
                    if (await reader.ReadAsync())
                    {
                        // Hash password into SHA-256
                        using (SHA256 sha256Hash = SHA256.Create())
                        {
                            byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(userLogin.Password));
                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < bytes.Length; i++)
                            {
                                builder.Append(bytes[i].ToString("x2"));
                            }
                            string hashedPassword = builder.ToString();

                            if (hashedPassword == reader.GetString(3))
                            {
                                return new User
                                {
                                    Id = reader.GetInt32(0),
                                    Username = reader.GetString(1),
                                    Name = reader.GetString(2),
                                    Role = reader.GetString(4),
                                    CreatedOn = reader.GetDateTime(5)
                                };
                            }
                        }
                    }
                }
            }
            return Unauthorized(); // Or return a more specific error
        }

        // POST: /Users
        [HttpPost]
        public async Task<ActionResult<User>> CreateUser(User user)
        {
            // Hash password here (use a library like BCrypt.Net)
            using (SHA256 sha256Hash = SHA256.Create())
            {
                byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(user.Password));
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < bytes.Length; i++)
                {
                    builder.Append(bytes[i].ToString("x2"));
                }
                user.Password = builder.ToString();
            }

            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("INSERT INTO doamais.users (username, name, password, role) VALUES (@username, @name, @password, @role) RETURNING id", conn))
                {
                    cmd.Parameters.AddWithValue("username", user.Username);
                    cmd.Parameters.AddWithValue("name", user.Name);
                    cmd.Parameters.AddWithValue("password", user.Password); // Store the hashed password
                    cmd.Parameters.AddWithValue("role", user.Role);
                    var newId = (int)await cmd.ExecuteScalarAsync();
                    user.Id = newId;
                    user.CreatedOn = DateTime.Now;
                    return CreatedAtAction(nameof(GetUser), new { id = newId }, user);
                }
            }
        }

        // GET: /Users/5
        [HttpGet("{id}")]
        public async Task<ActionResult<User>> GetUser(int id)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("SELECT * FROM doamais.users WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", id);
                    var reader = await cmd.ExecuteReaderAsync();
                    if (await reader.ReadAsync())
                    {
                        return new User
                        {
                            Id = reader.GetInt32(0),
                            Username = reader.GetString(1),
                            Name = reader.GetString(2),
                            Role = reader.GetString(4),
                            CreatedOn = reader.GetDateTime(5)
                        };
                    }
                }
            }
            return NotFound();
        }

        // Helper class for login
        public class UserLogin
        {
            public required string Username { get; set; }
            public required string Password { get; set; }
        }
    }
}
