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
    public class BeneficiariosController : ControllerBase
    {
        private readonly string _connectionString;

        public BeneficiariosController(IConfiguration configuration)
        {
            _connectionString = configuration.GetConnectionString("DoaMaisDatabase");
            if (string.IsNullOrEmpty(_connectionString))
            {
                throw new ArgumentNullException(_connectionString);
            }
        }

        // GET: /Beneficiarios
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Beneficiario>>> GetBeneficiarios()
        {
            var client = new DoaMaisTerraServiceClient();
            var response = await client.GetAllBeneficiariosAsync();
            return Ok(response);
        }

        // GET: /Beneficiarios/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Beneficiario>> GetBeneficiario(int id)
        {
            var client = new DoaMaisTerraServiceClient();
            var response = await client.GetBeneficiarioByIdAsync(id);
            return Ok(response);
        }

        // POST: /Beneficiarios
        [HttpPost]
        public async Task<IActionResult> PostBeneficiario(Beneficiario beneficiario)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.AddBeneficiarioAsync(beneficiario);
            return Ok();
        }

        // PUT: /Beneficiarios/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutBeneficiario(Beneficiario beneficiario)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.UpdateBeneficiarioAsync(beneficiario);
            return NoContent();
        }

        // DELETE: /Beneficiarios/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteBenificario(int id)
        {
            var client = new DoaMaisTerraServiceClient();
            await client.DeleteBeneficiarioAsync(id);
            return NoContent();
        }
    }
}
