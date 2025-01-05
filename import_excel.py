import pandas as pd
from sqlalchemy import create_engine, text
from sqlalchemy.exc import SQLAlchemyError
import os
#from dotenv import load_dotenv

#load_dotenv()

# Database credentials from environment variables
db_host = "localhost"#os.getenv("DB_HOST")
db_name = "doamais"#os.getenv("DB_NAME")
db_user = "postgres"#os.getenv("DB_USER")
db_password = "alexis27"#os.getenv("DB_PASSWORD")
db_port = os.getenv("DB_PORT", 5432)  # Default PostgreSQL port

# Construct the database connection URL
db_url = f"postgresql://{db_user}:{db_password}@{db_host}:{db_port}/{db_name}"


def import_excel_to_postgres(excel_file_path, db_url):
    """
    Imports data from an Excel file to a PostgreSQL database.

    Args:
        excel_file_path (str): Path to the Excel file.
        db_url (str): PostgreSQL database connection URL.
    """
    try:
        # Read the Excel file using pandas
        df = pd.read_excel(excel_file_path)
        # Rename columns to match the database table structure
        df = df.rename(
            columns={
                "ID": "id",
                "Nome": "nome_representante",
                "Telemóvel": "contacto",
                "Nacionalidade": "nacionalidade",
                "Família de": "dimensao_agregado",
                "Referência": "referencia",
                "Notas": "notas",
            }
        )
        # Drop rows where 'id' column is NaN (empty)
        df = df.dropna(subset=["id"])
        # Convert 'id' to integer
        df["id"] = df["id"].astype(int)

        # if dimension is not a number, set to 0
        df["dimensao_agregado"] = df["dimensao_agregado"].apply(
            lambda x: 0 if not isinstance(x, int) else x
        )

        # Handle NaN values in 'dimensao_agregado' by setting to None
        df["dimensao_agregado"] = df["dimensao_agregado"].fillna(0).astype(int)

        # Handle NaN values in 'referencia' and 'notas' by setting to an empty string
        df["referencia"] = df["referencia"].fillna("")
        df["notas"] = df["notas"].fillna("")

        # Select only the desired columns
        df = df[["id", "nome_representante", "contacto", "nacionalidade", "dimensao_agregado", "referencia", "notas"]]
        # Create a database engine
        engine = create_engine(db_url)

        # Insert data into the table using a raw SQL insert
        with engine.begin() as connection:
          for row in df.to_dict(orient='records'):
                query = text(
                """
                    INSERT INTO doamais.beneficiario (
                        id, 
                        nome_representante, 
                        contacto, 
                        nacionalidade, 
                        dimensao_agregado, 
                        referencia, 
                        notas
                    )
                    VALUES (
                        :id,
                        :nome_representante,
                        :contacto,
                        :nacionalidade,
                        :dimensao_agregado,
                        :referencia,
                        :notas
                    )
                    ON CONFLICT (id) DO UPDATE SET
                        nome_representante = :nome_representante,
                        contacto = :contacto,
                        nacionalidade = :nacionalidade,
                        dimensao_agregado = :dimensao_agregado,
                        referencia = :referencia,
                        notas = :notas;
                """
              )
                connection.execute(query, row)

        print("Data successfully imported to PostgreSQL.")
    except FileNotFoundError:
        print(f"Error: Excel file not found at {excel_file_path}")
    except SQLAlchemyError as e:
       print(f"A database error occurred: {e}")
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

if __name__ == "__main__":
    excel_file = "excel.xlsx"  # Replace with your file path
    import_excel_to_postgres(excel_file, db_url)