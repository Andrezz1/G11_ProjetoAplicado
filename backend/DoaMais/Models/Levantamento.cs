// Models/Levantamento.cs
namespace DoaMais.Models
{
    public class Levantamento
    {
        public int Id { get; set; }
        public int BeneficiarioId { get; set; }
        public required string TipoBens { get; set; }
        public DateTime DataLevantamento { get; set; }
        public int CreatedBy { get; set; }
    }
}