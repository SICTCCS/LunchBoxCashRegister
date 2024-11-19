#!/usr/bin/env python3

"""
Database Setup Script for LunchBox Cash Register System

This script initializes and configures the MariaDB database for the LunchBox Cash Register system.
It creates the FoodQuantity table for tracking transactions.
"""

import os
import sys
import pymysql
import logging
from datetime import datetime

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler('database_setup.log'),
        logging.StreamHandler(sys.stdout)
    ]
)

# Database configuration
DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',
    'password': os.getenv('MYSQL_PWD', 'Password1'),
    'database': 'lunchbox'
}

# SQL statements for database setup
SQL_STATEMENTS = {
    'create_database': "CREATE DATABASE IF NOT EXISTS lunchbox",
    
    'create_food_quantity_table': """
        CREATE TABLE IF NOT EXISTS FoodQuantity (
            meal INT,
            dessert_side INT,
            entree INT,
            soup INT,
            cookie INT,
            roll INT,
            date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            togo BOOLEAN
        )
    """
}

def create_database_connection(database=None):
    """Create a database connection with optional database selection."""
    try:
        connection = pymysql.connect(
            host=DB_CONFIG['host'],
            user=DB_CONFIG['user'],
            password=DB_CONFIG['password'],
            database=database
        )
        logging.info(f"Successfully connected to MySQL{' - ' + database if database else ''}")
        return connection
    except Exception as e:
        logging.error(f"Failed to connect to database: {str(e)}")
        sys.exit(1)

def setup_database():
    """Initialize database and create FoodQuantity table."""
    try:
        # Create initial connection without database
        conn = create_database_connection()
        cursor = conn.cursor()
        
        # Create database if it doesn't exist
        logging.info("Creating database if it doesn't exist...")
        cursor.execute(SQL_STATEMENTS['create_database'])
        conn.commit()
        
        # Close initial connection
        cursor.close()
        conn.close()
        
        # Reconnect with database selected
        conn = create_database_connection(DB_CONFIG['database'])
        cursor = conn.cursor()
        
        # Create FoodQuantity table
        logging.info("Creating FoodQuantity table...")
        cursor.execute(SQL_STATEMENTS['create_food_quantity_table'])
        
        conn.commit()
        logging.info("Database setup completed successfully")
        
    except Exception as e:
        logging.error(f"Database setup failed: {str(e)}")
        sys.exit(1)
        
    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'conn' in locals():
            conn.close()

def verify_setup():
    """Verify that the FoodQuantity table was created correctly."""
    try:
        conn = create_database_connection(DB_CONFIG['database'])
        cursor = conn.cursor()
        
        # Check if FoodQuantity table exists
        cursor.execute("SHOW TABLES")
        tables = cursor.fetchall()
        if ('FoodQuantity',) not in tables:
            logging.error("Missing table: FoodQuantity")
            return False
                
        logging.info("Database verification completed successfully")
        return True
        
    except Exception as e:
        logging.error(f"Verification failed: {str(e)}")
        return False
        
    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'conn' in locals():
            conn.close()

if __name__ == "__main__":
    logging.info("Starting database setup...")
    setup_database()
    
    if verify_setup():
        logging.info("Database setup and verification completed successfully")
        sys.exit(0)
    else:
        logging.error("Database setup or verification failed")
        sys.exit(1) 