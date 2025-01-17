﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Esse código foi gerado por uma ferramenta.
//
//     As alterações no arquivo poderão causar comportamento incorreto e serão perdidas se
//     o código for gerado novamente.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Terra
{
    using System.Runtime.Serialization;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Beneficiario", Namespace="http://schemas.datacontract.org/2004/07/Terra")]
    public partial class Beneficiario : object
    {
        
        private string ContactoField;
        
        private int DimensaoAgregadoField;
        
        private int IdField;
        
        private string NacionalidadeField;
        
        private string NomeRepresentanteField;
        
        private string NotasField;
        
        private string ReferenciaField;
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Contacto
        {
            get
            {
                return this.ContactoField;
            }
            set
            {
                this.ContactoField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int DimensaoAgregado
        {
            get
            {
                return this.DimensaoAgregadoField;
            }
            set
            {
                this.DimensaoAgregadoField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int Id
        {
            get
            {
                return this.IdField;
            }
            set
            {
                this.IdField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Nacionalidade
        {
            get
            {
                return this.NacionalidadeField;
            }
            set
            {
                this.NacionalidadeField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string NomeRepresentante
        {
            get
            {
                return this.NomeRepresentanteField;
            }
            set
            {
                this.NomeRepresentanteField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Notas
        {
            get
            {
                return this.NotasField;
            }
            set
            {
                this.NotasField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Referencia
        {
            get
            {
                return this.ReferenciaField;
            }
            set
            {
                this.ReferenciaField = value;
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Turno", Namespace="http://schemas.datacontract.org/2004/07/Terra")]
    public partial class Turno : object
    {
        
        private int IdField;
        
        private string TarefasField;
        
        private System.DateTime TimeField;
        
        private int UserIdField;
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int Id
        {
            get
            {
                return this.IdField;
            }
            set
            {
                this.IdField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Tarefas
        {
            get
            {
                return this.TarefasField;
            }
            set
            {
                this.TarefasField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public System.DateTime Time
        {
            get
            {
                return this.TimeField;
            }
            set
            {
                this.TimeField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int UserId
        {
            get
            {
                return this.UserIdField;
            }
            set
            {
                this.UserIdField = value;
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="Terra.IDoaMaisTerraService")]
    public interface IDoaMaisTerraService
    {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/GetBeneficiarioById", ReplyAction="http://tempuri.org/IDoaMaisTerraService/GetBeneficiarioByIdResponse")]
        System.Threading.Tasks.Task<Terra.Beneficiario> GetBeneficiarioByIdAsync(int id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/GetAllBeneficiarios", ReplyAction="http://tempuri.org/IDoaMaisTerraService/GetAllBeneficiariosResponse")]
        System.Threading.Tasks.Task<Terra.Beneficiario[]> GetAllBeneficiariosAsync();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/AddBeneficiario", ReplyAction="http://tempuri.org/IDoaMaisTerraService/AddBeneficiarioResponse")]
        System.Threading.Tasks.Task AddBeneficiarioAsync(Terra.Beneficiario beneficiario);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/UpdateBeneficiario", ReplyAction="http://tempuri.org/IDoaMaisTerraService/UpdateBeneficiarioResponse")]
        System.Threading.Tasks.Task UpdateBeneficiarioAsync(Terra.Beneficiario beneficiario);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/DeleteBeneficiario", ReplyAction="http://tempuri.org/IDoaMaisTerraService/DeleteBeneficiarioResponse")]
        System.Threading.Tasks.Task DeleteBeneficiarioAsync(int id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/GetTurnoById", ReplyAction="http://tempuri.org/IDoaMaisTerraService/GetTurnoByIdResponse")]
        System.Threading.Tasks.Task<Terra.Turno> GetTurnoByIdAsync(int id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/GetAllTurnos", ReplyAction="http://tempuri.org/IDoaMaisTerraService/GetAllTurnosResponse")]
        System.Threading.Tasks.Task<Terra.Turno[]> GetAllTurnosAsync(int user_id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/AddTurno", ReplyAction="http://tempuri.org/IDoaMaisTerraService/AddTurnoResponse")]
        System.Threading.Tasks.Task AddTurnoAsync(Terra.Turno turno);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/UpdateTurno", ReplyAction="http://tempuri.org/IDoaMaisTerraService/UpdateTurnoResponse")]
        System.Threading.Tasks.Task UpdateTurnoAsync(Terra.Turno turno);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IDoaMaisTerraService/DeleteTurno", ReplyAction="http://tempuri.org/IDoaMaisTerraService/DeleteTurnoResponse")]
        System.Threading.Tasks.Task DeleteTurnoAsync(int id);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public interface IDoaMaisTerraServiceChannel : Terra.IDoaMaisTerraService, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.2.0-preview1.23462.5")]
    public partial class DoaMaisTerraServiceClient : System.ServiceModel.ClientBase<Terra.IDoaMaisTerraService>, Terra.IDoaMaisTerraService
    {
        
        /// <summary>
        /// Implemente este método parcial para configurar o ponto de extremidade de serviço.
        /// </summary>
        /// <param name="serviceEndpoint">O ponto de extremidade a ser configurado</param>
        /// <param name="clientCredentials">As credenciais do cliente</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public DoaMaisTerraServiceClient() : 
                base(DoaMaisTerraServiceClient.GetDefaultBinding(), DoaMaisTerraServiceClient.GetDefaultEndpointAddress())
        {
            this.Endpoint.Name = EndpointConfiguration.BasicHttpBinding_IDoaMaisTerraService.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public DoaMaisTerraServiceClient(EndpointConfiguration endpointConfiguration) : 
                base(DoaMaisTerraServiceClient.GetBindingForEndpoint(endpointConfiguration), DoaMaisTerraServiceClient.GetEndpointAddress(endpointConfiguration))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public DoaMaisTerraServiceClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(DoaMaisTerraServiceClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public DoaMaisTerraServiceClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(DoaMaisTerraServiceClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress)
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public DoaMaisTerraServiceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        public System.Threading.Tasks.Task<Terra.Beneficiario> GetBeneficiarioByIdAsync(int id)
        {
            return base.Channel.GetBeneficiarioByIdAsync(id);
        }
        
        public System.Threading.Tasks.Task<Terra.Beneficiario[]> GetAllBeneficiariosAsync()
        {
            return base.Channel.GetAllBeneficiariosAsync();
        }
        
        public System.Threading.Tasks.Task AddBeneficiarioAsync(Terra.Beneficiario beneficiario)
        {
            return base.Channel.AddBeneficiarioAsync(beneficiario);
        }
        
        public System.Threading.Tasks.Task UpdateBeneficiarioAsync(Terra.Beneficiario beneficiario)
        {
            return base.Channel.UpdateBeneficiarioAsync(beneficiario);
        }
        
        public System.Threading.Tasks.Task DeleteBeneficiarioAsync(int id)
        {
            return base.Channel.DeleteBeneficiarioAsync(id);
        }
        
        public System.Threading.Tasks.Task<Terra.Turno> GetTurnoByIdAsync(int id)
        {
            return base.Channel.GetTurnoByIdAsync(id);
        }
        
        public System.Threading.Tasks.Task<Terra.Turno[]> GetAllTurnosAsync(int user_id)
        {
            return base.Channel.GetAllTurnosAsync(user_id);
        }
        
        public System.Threading.Tasks.Task AddTurnoAsync(Terra.Turno turno)
        {
            return base.Channel.AddTurnoAsync(turno);
        }
        
        public System.Threading.Tasks.Task UpdateTurnoAsync(Terra.Turno turno)
        {
            return base.Channel.UpdateTurnoAsync(turno);
        }
        
        public System.Threading.Tasks.Task DeleteTurnoAsync(int id)
        {
            return base.Channel.DeleteTurnoAsync(id);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.BasicHttpBinding_IDoaMaisTerraService))
            {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            throw new System.InvalidOperationException(string.Format("Não foi possível encontrar o ponto de extremidade com o nome \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.BasicHttpBinding_IDoaMaisTerraService))
            {
                return new System.ServiceModel.EndpointAddress("http://localhost:5010/Service.wsdl");
            }
            throw new System.InvalidOperationException(string.Format("Não foi possível encontrar o ponto de extremidade com o nome \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.Channels.Binding GetDefaultBinding()
        {
            return DoaMaisTerraServiceClient.GetBindingForEndpoint(EndpointConfiguration.BasicHttpBinding_IDoaMaisTerraService);
        }
        
        private static System.ServiceModel.EndpointAddress GetDefaultEndpointAddress()
        {
            return DoaMaisTerraServiceClient.GetEndpointAddress(EndpointConfiguration.BasicHttpBinding_IDoaMaisTerraService);
        }
        
        public enum EndpointConfiguration
        {
            
            BasicHttpBinding_IDoaMaisTerraService,
        }
    }
}
