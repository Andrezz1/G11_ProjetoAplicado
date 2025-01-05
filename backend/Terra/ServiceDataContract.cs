using System.Runtime.Serialization;
using System.ServiceModel;

namespace Terra {
    // Data Contract for Beneficiario
    [DataContract]
    public class Beneficiario
    {
        [DataMember]
        public int Id { get; set; }

        [DataMember]
        public required string NomeRepresentante { get; set; }

        [DataMember]
        public required string Contacto { get; set; }

        [DataMember]
        public string Nacionalidade { get; set; } = "Português";

        [DataMember]
        public int DimensaoAgregado { get; set; }

        [DataMember]
        public string Referencia { get; set; }

        [DataMember]
        public string Notas { get; set; }
    }

    // Data Contract for Turno
    [DataContract]
    public class Turno
    {
        [DataMember]
        public int Id { get; set; }

        [DataMember]
        public int UserId { get; set; }

        [DataMember]
        public DateTime Time { get; set; }

        [DataMember]
        public string Tarefas { get; set; }
    }

    // Service Contract
    [ServiceContract]
    public interface IDoaMaisTerraService
    {
        // Beneficiario Operations
        [OperationContract]
        Beneficiario GetBeneficiarioById(int id);

        [OperationContract]
        IEnumerable<Beneficiario> GetAllBeneficiarios();

        [OperationContract]
        void AddBeneficiario(Beneficiario beneficiario);

        [OperationContract]
        void UpdateBeneficiario(Beneficiario beneficiario);

        [OperationContract]
        void DeleteBeneficiario(int id);

        // Turno Operations
        [OperationContract]
        Turno GetTurnoById(int id);

        [OperationContract]
        IEnumerable<Turno> GetAllTurnos(int user_id = 0);

        [OperationContract]
        void AddTurno(Turno turno);

        [OperationContract]
        void UpdateTurno(Turno turno);

        [OperationContract]
        void DeleteTurno(int id);
    }
}

