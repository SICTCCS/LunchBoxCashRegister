#!/usr/bin/env python3

"""
Database Setup Script for LunchBox Cash Register System

This script initializes and configures the MariaDB database for the LunchBox Cash Register system.
It creates necessary tables, users, and sets up proper permissions.

Tables created:
- users: Store login credentials and user roles
- transactions: Store all cash register transactions
- daily_totals: Store daily summary statistics
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
    'password': 'Password1',  # Should be changed in production
    'database': 'lunchbox'
}

# SQL statements for database setup
SQL_STATEMENTS = {
    'create_database': "CREATE DATABASE IF NOT EXISTS lunchbox",
    
    'create_users_table': """
        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(50) UNIQUE NOT NULL,
            password VARCHAR(255) NOT NULL,
            role ENUM('admin', 'cashier') NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
    """,
    
    'create_transactions_table': """
        CREATE TABLE IF NOT EXISTS transactions (
            id INT AUTO_INCREMENT PRIMARY KEY,
            meal INT DEFAULT 0,
            dessert_side INT DEFAULT 0,
            entree INT DEFAULT 0,
            soup INT DEFAULT 0,
            cookie INT DEFAULT 0,
            roll INT DEFAULT 0,
            total_price DECIMAL(10,2) NOT NULL,
            description TEXT,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
    """,
    
    'create_daily_totals_table': """
        CREATE TABLE IF NOT EXISTS daily_totals (
            id INT AUTO_INCREMENT PRIMARY KEY,
            date DATE UNIQUE NOT NULL,
            total_transactions INT DEFAULT 0,
            total_revenue DECIMAL(10,2) DEFAULT 0.00,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
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
    """Initialize database and create required tables."""
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
        
        # Create tables
        for table_name, sql in SQL_STATEMENTS.items():
            if table_name.startswith('create_') and table_name != 'create_database':
                logging.info(f"Creating table: {table_name}")
                cursor.execute(sql)
                
        # Create default admin user if it doesn't exist
        cursor.execute("""
            INSERT IGNORE INTO users (username, password, role)
            VALUES ('admin', 'admin123', 'admin')
        """)
        
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
    """Verify that all required database objects were created correctly."""
    try:
        conn = create_database_connection(DB_CONFIG['database'])
        cursor = conn.cursor()
        
        # Check tables
        cursor.execute("SHOW TABLES")
        tables = cursor.fetchall()
        expected_tables = ['users', 'transactions', 'daily_totals']
        
        for table in expected_tables:
            if (table,) not in tables:
                logging.error(f"Missing table: {table}")
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