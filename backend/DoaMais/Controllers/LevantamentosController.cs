// Controllers/LevantamentosController.cs
using Microsoft.AspNetCore.Mvc;
using DoaMais.Models;
using Npgsql;
using System.Threading.Tasks;
using System.Collections.Generic;
using Microsoft.Extensions.Configuration;

namespace DoaMais.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class LevantamentosController : ControllerBase
    {
        private readonly string _connectionString;

        public LevantamentosController(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
            if (string.IsNullOrEmpty(_connectionString))
            {
                throw new ArgumentNullException(_connectionString);
            }
        }

        // GET: /Levantamentos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Levantamento>>> GetLevantamentos()
        {
            var levantamentos = new List<Levantamento>();
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using var cmd = new NpgsqlCommand("SELECT * FROM doamais.levantamento", conn);
                var reader = await cmd.ExecuteReaderAsync();
                while (await reader.ReadAsync())
                {
                    levantamentos.Add(new Levantamento
                    {
                        Id = reader.GetInt32(0),
                        BeneficiarioId = reader.GetInt32(1),
                        TipoBens = reader.GetString(2),
                        DataLevantamento = reader.GetDateTime(3),
                        CreatedBy = reader.GetInt32(4)
                    });
                }
            }
            return Ok(levantamentos);
        }

        // GET: /Levantamentos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Levantamento>> GetLevantamento(int id)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using var cmd = new NpgsqlCommand("SELECT * FROM doamais.levantamento WHERE id = @id", conn);
                cmd.Parameters.AddWithValue("id", id);
                var reader = await cmd.ExecuteReaderAsync();
                if (await reader.ReadAsync())
                {
                    return new Levantamento
                    {
                        Id = reader.GetInt32(0),
                        BeneficiarioId = reader.GetInt32(1),
                        TipoBens = reader.GetString(2),
                        DataLevantamento = reader.GetDateTime(3),
                        CreatedBy = reader.GetInt32(4)
                    };
                }
            }
            return NotFound();
        }

        // POST: /Levantamentos
        [HttpPost]
        public async Task<ActionResult<Levantamento>> PostLevantamento(Levantamento levantamento)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("INSERT INTO doamais.levantamento (beneficiario_id, tipo_bens, data_levantamento, created_by) VALUES (@beneficiarioId, @tipoBens, @dataLevantamento, @createdBy) RETURNING id", conn))
                {
                    cmd.Parameters.AddWithValue("beneficiarioId", levantamento.BeneficiarioId);
                    cmd.Parameters.AddWithValue("tipoBens", levantamento.TipoBens);
                    cmd.Parameters.AddWithValue("dataLevantamento", levantamento.DataLevantamento);
                    cmd.Parameters.AddWithValue("createdBy", levantamento.CreatedBy);
                    var newId = (int)await cmd.ExecuteScalarAsync();
                    return CreatedAtAction(nameof(GetLevantamento), new { id = newId }, levantamento);
                }
            }
        }

        // PUT: /Levantamentos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutLevantamento(int id, Levantamento levantamento)
        {
            if (id != levantamento.Id)
            {
                return BadRequest();
            }
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("UPDATE doamais.levantamento SET beneficiario_id = @beneficiarioId, tipo_bens = @tipoBens, data_levantamento = @dataLevantamento, created_by = @createdBy WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", levantamento.Id);
                    cmd.Parameters.AddWithValue("beneficiarioId", levantamento.BeneficiarioId);
                    cmd.Parameters.AddWithValue("tipoBens", levantamento.TipoBens);
                    cmd.Parameters.AddWithValue("dataLevantamento", levantamento.DataLevantamento);
                    cmd.Parameters.AddWithValue("createdBy", levantamento.CreatedBy);
                    await cmd.ExecuteNonQueryAsync();
                }
            }
            return NoContent();
        }

        // DELETE: /Levantamentos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteLevantamento(int id)
        {
            using (var conn = new NpgsqlConnection(_connectionString))
            {
                await conn.OpenAsync();
                using (var cmd = new NpgsqlCommand("DELETE FROM doamais.levantamento WHERE id = @id", conn))
                {
                    cmd.Parameters.AddWithValue("id", id);
                    await cmd.ExecuteNonQueryAsync();
                }
            }
            return NoContent();
        }
    }
}