#!/usr/bin/env python3

"""
Main API Server for LunchBox Cash Register System

This script provides the REST API endpoints for the cash register application.
It handles transaction processing, data storage, and communication with the 
MariaDB database.

Features:
- REST API endpoints for transactions
- Database connection management
- Transaction validation
- Error handling and logging
"""

from flask import Flask, request, jsonify
import pymysql
import logging
from datetime import datetime
import os

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    filename='api_server.log'
)

# Initialize Flask application
app = Flask(__name__)

# Database configuration
DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',
    'password': 'Password1',
    'database': 'lunchbox'
}

def get_db_connection():
    """
    Create and return a database connection.
    Includes error handling and logging.
    """
    try:
        connection = pymysql.connect(**DB_CONFIG)
        return connection
    except Exception as e:
        logging.error(f"Database connection failed: {str(e)}")
        return None

@app.route('/', methods=['POST'])
def process_transaction():
    """
    Main endpoint for processing cash register transactions.
    Accepts JSON data with item quantities and calculates total price.
    
    Expected JSON format:
    {
        "meal": int,
        "dessert_side": int,
        "entree": int,
        "soup": int,
        "cookie": int,
        "roll": int,
        "description": string
    }
    """
    try:
        # Get JSON data from request
        data = request.get_json()
        
        # Validate required fields
        required_fields = ['meal', 'dessert_side', 'entree', 'soup', 'cookie', 'roll']
        for field in required_fields:
            if field not in data:
                return jsonify({'error': f'Missing required field: {field}'}), 400
        
        # Calculate total price based on item quantities
        total_price = (
            data['meal'] * 8.00 +          # $8.00 per meal
            data['dessert_side'] * 2.00 +  # $2.00 per dessert
            data['entree'] * 3.00 +        # $3.00 per entree
            data['soup'] * 3.00 +          # $3.00 per soup
            data['cookie'] * 1.00 +        # $1.00 per cookie
            data['roll'] * 0.50            # $0.50 per roll
        )
        
        # Store transaction in database
        conn = get_db_connection()
        if not conn:
            return jsonify({'error': 'Database connection failed'}), 500
            
        with conn.cursor() as cursor:
            # Insert transaction record
            sql = """
                INSERT INTO transactions 
                (meal, dessert_side, entree, soup, cookie, roll, total_price, description)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
            """
            cursor.execute(sql, (
                data['meal'], data['dessert_side'], data['entree'],
                data['soup'], data['cookie'], data['roll'],
                total_price, data.get('description', '')
            ))
            
            # Update daily totals
            update_daily_totals(cursor, total_price)
            
            conn.commit()
            
        return jsonify({
            'status': 'success',
            'total_price': total_price,
            'timestamp': datetime.now().isoformat()
        })
        
    except Exception as e:
        logging.error(f"Transaction processing failed: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500
    
    finally:
        if 'conn' in locals():
            conn.close()

def update_daily_totals(cursor, total_price):
    """
    Update or create daily totals record for the current date.
    """
    today = datetime.now().date()
    
    # Try to update existing record first
    sql = """
        INSERT INTO daily_totals (date, total_transactions, total_revenue)
        VALUES (%s, 1, %s)
        ON DUPLICATE KEY UPDATE
        total_transactions = total_transactions + 1,
        total_revenue = total_revenue + %s
    """
    cursor.execute(sql, (today, total_price, total_price))

if __name__ == '__main__':
    # Start Flask application
    app.run(
        host='0.0.0.0',  # Listen on all network interfaces
        port=8000,       # Run on port 8000
        debug=False      # Disable debug mode in production
    )


