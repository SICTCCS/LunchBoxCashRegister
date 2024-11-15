import pymysql
import sys

def setup_database():
    try:
        # Connect to MySQL/MariaDB
        connection = pymysql.connect(
            host='localhost',
            user='root',
            password='root',
            charset='utf8mb4'
        )
        
        cursor = connection.cursor()
        
        # Create database if it doesn't exist
        cursor.execute("CREATE DATABASE IF NOT EXISTS lunchbox")
        cursor.execute("USE lunchbox")
        
        # Create FoodQuantity table
        create_table_query = """
        CREATE TABLE IF NOT EXISTS FoodQuantity (
            meal INT,
            dessert_side INT,
            entree INT,
            soup INT,
            cookie INT,
            roll INT,
            date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            togo BOOLEAN,
            description VARCHAR(255)
        )
        """
        cursor.execute(create_table_query)
        
        connection.commit()
        print("Database and table created successfully!")
        
    except pymysql.Error as e:
        print(f"Error: {e}")
        sys.exit(1)
        
    finally:
        if 'connection' in locals() and connection.open:
            connection.close()

if __name__ == "__main__":
    setup_database() 