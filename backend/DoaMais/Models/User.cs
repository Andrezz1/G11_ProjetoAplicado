// Models/User.cs
namespace DoaMais.Models
{
    public class User
    {
        public int Id { get; set; }
        public required string Username { get; set; }
        public required string Name { get; set; }
        public string Password { get; set; }
        public required string Role { get; set; }
        public DateTime CreatedOn { get; set; }
    }
}