using SoapCore;
using Terra;
using Terra.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
//builder.Services.AddSwaggerGen();
builder.Services.AddSoapCore();
builder.Services.AddSingleton<IDoaMaisTerraService, BeneficiarioTurnoService>();

var app = builder.Build();

app.UseSoapEndpoint<IDoaMaisTerraService>("/Service.wsdl", new SoapEncoderOptions());


// Configure the HTTP request pipeline.
//if (app.Environment.IsDevelopment())
//{
//    app.UseSwagger();
//    app.UseSwaggerUI();
//}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Urls.Add("http://0.0.0.0:5010");

app.Run();
