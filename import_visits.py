import pandas as pd
import psycopg2
from datetime import datetime
import os
import math



def import_visits_from_excel(file_path, db_params):
    """
    Imports visit data from an Excel file into a PostgreSQL database.

    Args:
        file_path (str): The path to the Excel file.
        db_params (dict): A dictionary containing database connection parameters:
            - host (str): The database host.
            - database (str): The database name.
            - user (str): The database user.
            - password (str): The database password.
            - port (int, optional): The database port (default is 5432).
    """

    try:
        # Read the Excel file into a pandas DataFrame
        df = pd.read_excel(file_path)

        # Rename the first column 'ID' for consistent handling
        df = df.rename(columns={df.columns[0]: 'ID'})

        # Convert dates in header from "m/d/yyyy" format to "yyyy-mm-dd" format
        date_cols = []
        for col in df.columns:
            try:
                if type(col) == datetime:
                    new_col_name = col.strftime('%Y-%m-%d')
                    df.rename(columns={col: new_col_name}, inplace=True)
                    date_cols.append(new_col_name)
                    continue
                #date_obj = datetime.strptime(col, '%m/%d/%Y')

            except ValueError:
                # Ignore non-date columns
                continue
        
        # Create a connection to the PostgreSQL database
        conn = psycopg2.connect(**db_params)
        cur = conn.cursor()


        # Iterate through each row in the DataFrame
        for _, row in df.iterrows():
            beneficiario_id = row['ID']
            for col in date_cols:
                if pd.notna(row[col]) and row[col] == 1: #Check if the visit exists
                    try:
                        
                        date_obj = datetime.strptime(col, '%Y-%m-%d').date()
                    except ValueError:
                        print(f"Error parsing date: {col} for beneficiario {beneficiario_id}")
                        continue

                    # Insert the visit into the database
                    query = """
                                INSERT INTO doamais.visita (beneficiario_id, date)
                                VALUES (%s, %s)
                            """
                    if math.isnan(beneficiario_id):
                        print(f"beneficiario_id is NaN")
                        continue
                    print(f"Inserting beneficiario_id: {int(beneficiario_id)}, date: {date_obj}")
                    cur.execute(query, (beneficiario_id, date_obj))

        # Commit the changes and close the connection
        conn.commit()
        cur.close()
        conn.close()
        print("Data import successful!")

    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
    except pd.errors.ParserError as e:
         print(f"Error parsing Excel: {e}")
    ##except psycopg2.Error as e:
    ##    print(f"Database error: {e}")
    ##except Exception as e:
    ##     print(f"An unexpected error occured: {e}")

if __name__ == "__main__":
    # Database connection parameters
    db_params = {
        "host": os.environ.get("POSTGRES_HOST", "localhost"),
        "database": os.environ.get("POSTGRES_DB", "doamais"),
        "user": os.environ.get("POSTGRES_USER", "postgres"),
        "password": os.environ.get("POSTGRES_PASSWORD", "alexis27"),
        "port": int(os.environ.get("POSTGRES_PORT", 5432)),  # Convert port to integer
    }
    file_path = "excel.xlsx"
    import_visits_from_excel(file_path, db_params)