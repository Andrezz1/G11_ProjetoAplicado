// Controllers/VisitasController.cs
using Microsoft.AspNetCore.Mvc;
using DoaMais.Models;
using Npgsql;


namespace DoaMaisApi.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class VisitasController : ControllerBase
    {
        private readonly string _connectionString;

        public VisitasController(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
        }

        // GET: /Visitas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Visita>>> GetVisitas()
        {
            var visitas = new List<Visita>();
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("SELECT * FROM doamais.visita", conn))
                {
                    var reader = await cmd.ExecuteReaderAsync();
                    while (await reader.ReadAsync())
                    {
                        visitas.Add(new Visita
                        {
                            Id = reader.GetInt32(0),
                            BeneficiarioId = reader.GetInt32(1),
                            Date = reader.GetDateTime(2)
                        });
                    }
                }
            }
            return Ok(visitas);
        }

        // GET: /Visitas/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Visita>> GetVisita(int id)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("SELECT * FROM doamais.visita WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", id);
                    var reader = await cmd.ExecuteReaderAsync();
                    if (await reader.ReadAsync())
                    {
                        return new Visita
                        {
                            Id = reader.GetInt32(0),
                            BeneficiarioId = reader.GetInt32(1),
                            Date = reader.GetDateTime(2)
                        };
                    }
                }
            }
            return NotFound();
        }

        // POST: /Visitas
        [HttpPost]
        public async Task<ActionResult<Visita>> PostVisita(Visita visita)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("INSERT INTO doamais.visita (beneficiario_id, date) VALUES (@beneficiarioId, @date) RETURNING id", conn))
                {
                    cmd.Parameters.AddWithValue("beneficiarioId", visita.BeneficiarioId);
                    cmd.Parameters.AddWithValue("date", visita.Date);
                    var newId = (int)await cmd.ExecuteScalarAsync();
                    return CreatedAtAction(nameof(GetVisita), new { id = newId }, visita);
                }
            }
        }

        // PUT: /Visitas/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutVisita(int id, Visita visita)
        {
            if (id != visita.Id)
            {
                return BadRequest();
            }
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("UPDATE doamais.visita SET beneficiario_id = @beneficiarioId, date = @date WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", visita.Id);
                    cmd.Parameters.AddWithValue("beneficiarioId", visita.BeneficiarioId);
                    cmd.Parameters.AddWithValue("date", visita.Date);
                    await cmd.ExecuteNonQueryAsync();
                }
            }
            return NoContent();
        }

        // DELETE: /Visitas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteVisita(int id)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("DELETE FROM doamais.visita WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", id);
                    await cmd.ExecuteNonQueryAsync();
                }
            }
            return NoContent();
        }
    }
}