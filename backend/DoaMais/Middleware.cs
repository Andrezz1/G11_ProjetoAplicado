using Microsoft.Extensions.Configuration;
using Npgsql;
using System.Security.Cryptography;
using System.Text;

namespace DoaMais
{
    public class Middleware
    {
        private string _connectionString;

        public Middleware(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
            if (string.IsNullOrEmpty(_connectionString))
            {
                throw new ArgumentNullException(_connectionString);
            }
        }
        public async Task CheckAuth(HttpContext context, Func<Task> next)
        {
            // Check if the request is for the Swagger UI
            if (context.Request.Path.StartsWithSegments("/swagger"))
            {
                await next.Invoke();
                return;
            }

            // Don't check for authentication if the request is for the login endpoint
            if (context.Request.Path.StartsWithSegments("/Users/Login"))
            {
                await next.Invoke();
                return;
            }

            // Get the Authorization header
            var authHeader = context.Request.Headers["Authorization"];
            if (authHeader.Count == 0)
            {
                context.Response.StatusCode = 401;
                await context.Response.WriteAsync("Authorization header is missing");
                return;
            }
            // Decode the base64 string
            // string is username;password
            var authHeaderString = Encoding.UTF8.GetString(Convert.FromBase64String(authHeader));
            var authHeaderParts = authHeaderString.Split(";");
            if (authHeaderParts.Length != 2)
            {
                context.Response.StatusCode = 401;
                await context.Response.WriteAsync("Authorization header is invalid");
                return;
            }
            var username = authHeaderParts[0];
            var password = authHeaderParts[1];
            // Check the username and password

            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("SELECT password, role FROM doamais.users WHERE username = @username", conn))
                {
                    cmd.Parameters.AddWithValue("username", username);
                    var reader = await cmd.ExecuteReaderAsync();
                    if (await reader.ReadAsync())
                    {
                        // Hash password into SHA-256
                        using (SHA256 sha256Hash = SHA256.Create())
                        {
                            byte[] bytes = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(password));
                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < bytes.Length; i++)
                            {
                                builder.Append(bytes[i].ToString("x2"));
                            }
                            string hashedPassword = builder.ToString();
                            if (hashedPassword == reader.GetString(0))
                            {
                                context.Session.SetString("role", reader.GetString(1));
                                await next.Invoke();
                            }
                            else
                            {
                                context.Response.StatusCode = 401;
                                await context.Response.WriteAsync("Invalid username or password");
                            }
                        }
                    }
                    else
                    {
                        context.Response.StatusCode = 401;
                        await context.Response.WriteAsync("Invalid username or password");
                    }
                }

            }
        }

        public async Task AdminCheck(HttpContext context, Func<Task> next)
        {
            var routes = new List<Route>
            {
                new() { Method = "POST", RoutePath = "/Users" },
                new() { Method = "POST", RoutePath = "/Turno" },
                new() { Method = "PUT", RoutePath = "/Turno/" },
                new() { Method = "DELETE", RoutePath = "/Turno/" }
            };

            // Lógica adicional para verificar se a rota atual está na lista de rotas
            var currentRoute = context.Request.Path;
            var currentMethod = context.Request.Method;

            Console.WriteLine(currentRoute);
            Console.WriteLine(currentMethod);

            foreach (var route in routes)
            {
                if (currentRoute.StartsWithSegments(route.RoutePath) && currentRoute != "/Users/Login"
                && currentMethod.Equals(route.Method, StringComparison.OrdinalIgnoreCase))
                {
                    // Verifique se o usuário tem permissão de administrador
                    var role = context.Session.GetString("role");
                    if (role != "admin")
                    {
                        context.Response.StatusCode = 403;
                        await context.Response.WriteAsync("Forbidden: Admins only");
                        return;
                    }
                }
            }

            await next.Invoke();
        }
    }

    internal interface IRoute
    {
        string Method { get; set; }
        string RoutePath { get; set; }
    }

    internal class Route : IRoute
    {
        public required string Method { get; set; }
        public required string RoutePath { get; set; }
    }
}
