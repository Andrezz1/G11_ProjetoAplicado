using Npgsql;
using Terra.Utils;

namespace Terra.Services
{
    public class BeneficiarioTurnoService : IDoaMaisTerraService
    {
        public void AddBeneficiario(Beneficiario beneficiario)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            //conn.Open();
            using var tx = conn.BeginTransaction();
            NpgsqlCommand query = new("INSERT INTO doamais.beneficiario (nome_representante, contacto, nacionalidade, dimensao_agregado, referencia, notas) VALUES ($1, $2, $3, $4, $5, $6)", conn, tx)
            {
                Parameters =
                {
                    new() { Value = beneficiario.NomeRepresentante },
                    new() { Value = beneficiario.Contacto },
                    new() { Value = beneficiario.Nacionalidade },
                    new() { Value = beneficiario.DimensaoAgregado },
                    new() { Value = beneficiario.Referencia },
                    new() { Value = beneficiario.Notas },
                }
            };
            query.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }

        public void DeleteBeneficiario(int id)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var tx = conn.BeginTransaction();
            NpgsqlCommand query = new("DELETE FROM doamais.beneficiario WHERE id = $1", conn, tx)
            {
                Parameters =
                {
                    new() { Value = id },
                }
            };
            query.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }

        public void UpdateBeneficiario(Beneficiario beneficiario)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var tx = conn.BeginTransaction();

            NpgsqlCommand query = new("UPDATE doamais.beneficiario SET nome_representante = $1, contacto = $2, nacionalidade = $3, dimensao_agregado = $4, referencia = $6, notas = $7 WHERE id = $5", conn, tx)
            {
                Parameters =
                {
                    new() { Value = beneficiario.NomeRepresentante },
                    new() { Value = beneficiario.Contacto },
                    new() { Value = beneficiario.Nacionalidade },
                    new() { Value = beneficiario.DimensaoAgregado },
                    new() { Value = beneficiario.Id },
                    new() { Value = beneficiario.Referencia },
                    new() { Value = beneficiario.Notas },
                }
            };
            query.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }

        public IEnumerable<Beneficiario> GetAllBeneficiarios()
        {
            DbConnector db = new();
            List<Beneficiario> beneficiarios = [];

            using var dataSource = db.GetDataSource();

            using (var cmd = dataSource.CreateCommand("SELECT id, nome_representante, contacto, nacionalidade, dimensao_agregado, referencia, notas FROM doamais.beneficiario"))
            using (var reader = cmd.ExecuteReader())
            {
                while (reader.Read())
                {
                    beneficiarios.Add(new Beneficiario
                    {
                        Id = reader.GetInt32(0),
                        NomeRepresentante = reader.GetString(1),
                        Contacto = reader.GetString(2),
                        Nacionalidade = reader.GetString(3),
                        DimensaoAgregado = reader.GetInt32(4),
                        Referencia = reader.GetString(5),
                        Notas = reader.GetString(6)
                    });
                }
            }

            return beneficiarios;
        }
        public Beneficiario GetBeneficiarioById(int id)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var cmd = new NpgsqlCommand("SELECT id, nome_representante, contacto, nacionalidade, dimensao_agregado, referencia, notas FROM doamais.beneficiario WHERE id = $1", conn)
            {
                Parameters =
                {
                    new() { Value = id },
                }
            };
            using var reader = cmd.ExecuteReader();
            Beneficiario? beneficiario;
            if (reader.Read())
            {
                beneficiario = new Beneficiario
                {
                    Id = reader.GetInt32(0),
                    NomeRepresentante = reader.GetString(1),
                    Contacto = reader.GetString(2),
                    Nacionalidade = reader.GetString(3),
                    DimensaoAgregado = reader.GetInt32(4),
                    Referencia = reader.GetString(5),
                    Notas = reader.GetString(6)
                };
            }
            else
            {
                // No data found, handle accordingly
                throw new Exception("Beneficiario not found");
            }

            conn.Close();
           

            return beneficiario;
        }

        public void AddTurno(Turno turno)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var tx = conn.BeginTransaction();
            using var cmd = new NpgsqlCommand("INSERT INTO doamais.turno (user_id, time, tarefas) VALUES ($1, $2, $3)", conn, tx)
            {
                Parameters =
                {
                    new() { Value = turno.UserId },
                    new() { Value = turno.Time },
                    new() { Value = turno.Tarefas },
                }
            };
            cmd.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }

        public void DeleteTurno(int id)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var tx = conn.BeginTransaction();
            using var cmd = new NpgsqlCommand("DELETE FROM doamais.turno WHERE id = $1", conn, tx)
            {
                Parameters =
                {
                    new() { Value = id },
                }
            };
            cmd.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }

        public IEnumerable<Turno> GetAllTurnos(int user_id = 0)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            NpgsqlCommand cmd = new NpgsqlCommand("SELECT id, user_id, time, tarefas FROM doamais.turno", conn);
            if (user_id  != 0)
            {
                cmd = new NpgsqlCommand("SELECT id, user_id, time, tarefas FROM doamais.turno WHERE user_id = $1", conn)
                {
                    Parameters = { new() { Value = user_id } },
                };
            }
            using var reader = cmd.ExecuteReader();
            List<Turno> turnos = [];
            while (reader.Read())
            {
                turnos.Add(new Turno
                {
                    Id = reader.GetInt32(0),
                    UserId = reader.GetInt32(1),
                    Time = reader.GetDateTime(2),
                    Tarefas = reader.GetString(3)
                });
            }
            return turnos;
        }


        public Turno GetTurnoById(int id)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var cmd = new NpgsqlCommand("SELECT id, user_id, time, tarefas FROM doamais.turno WHERE id = $1", conn)
            {
                Parameters =
                {
                    new() { Value = id },
                }
            };
            using var reader = cmd.ExecuteReader();
            Turno turno;
            if (reader.Read())
            {
                turno = new Turno
                {
                    Id = reader.GetInt32(0),
                    UserId = reader.GetInt32(1),
                    Time = reader.GetDateTime(2),
                    Tarefas = reader.GetString(3)
                };
            }
            else
            {
                // No data found, handle accordingly
                throw new Exception("Turno not found");
            }
            return turno;
        }


        public void UpdateTurno(Turno turno)
        {
            DbConnector db = new();
            using var dataSource = db.GetDataSource();
            using var conn = dataSource.OpenConnection();
            using var tx = conn.BeginTransaction();
            using var cmd = new NpgsqlCommand(
                "UPDATE doamais.turno SET user_id = $1, time = $2, tarefas = $3 WHERE id = $4", conn, tx)
            {
                Parameters =
                {
                    new() { Value = turno.UserId },
                    new() { Value = turno.Time },
                    new() { Value = turno.Tarefas },
                    new() { Value = turno.Id },
                }
            };
            cmd.ExecuteNonQuery();
            tx.Commit();
            conn.Close();
        }
    }
}
