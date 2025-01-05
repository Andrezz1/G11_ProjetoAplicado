// Controllers/LevantamentosController.cs
using Microsoft.AspNetCore.Mvc;
using DoaMais.Models;
using Npgsql;
using System.Threading.Tasks;
using System.Collections.Generic;
using Microsoft.Extensions.Configuration;
using Terra;

namespace DoaMais.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class TurnoController : ControllerBase
    {
        private readonly string _connectionString;

        public TurnoController(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
            if (string.IsNullOrEmpty(_connectionString))
            {
                throw new ArgumentNullException(_connectionString);
            }
        }

        // GET: /Turno
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Turno>>> GetTurnos(int id)
        {
            var client = new DoaMaisTerraServiceClient();
            var response = await client.GetAllTurnosAsync(id);
            return Ok(response);
        }

        // GET: /Turno/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Turno>> GetTurno(int id)
        {
            var client = new DoaMaisTerraServiceClient();
            var response = await client.GetTurnoByIdAsync(id);
            return Ok(response);
        }

        // POST: /Turno
        [HttpPost]
        public async Task<IActionResult> PostTurno(Turno turno)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.AddTurnoAsync(turno);
            return NoContent();
        }

        // PUT: /Turno/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTurno(Turno turno)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.AddTurnoAsync(turno);
            return NoContent();
        }

        // DELETE: /Turno/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTurno(int id)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.DeleteTurnoAsync(id);
            return NoContent();
        }
    }
}
