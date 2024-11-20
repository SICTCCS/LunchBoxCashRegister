#!/usr/bin/env python3

"""
Web Interface Server for LunchBox Cash Register System

This script provides a web-based management interface for viewing
transactions, generating reports, and managing the system.

Features:
- Transaction history viewing
- Daily totals reporting
- CSV export functionality
- User authentication
"""

from flask import Flask, render_template, request, redirect, url_for, flash, send_file
import pymysql
import csv
from io import StringIO
from datetime import datetime
import logging
from functools import wraps

# Configure logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    filename='web_interface.log'
)

# Initialize Flask application
app = Flask(__name__)
app.secret_key = 'your-secret-key-here'  # Change in production

# Database configuration
DB_CONFIG = {
    'host': 'localhost',
    'user': 'root',
    'password': 'Password1',
    'database': 'lunchbox'
}

def login_required(f):
    """
    Decorator to require login for protected routes
    """
    @wraps(f)
    def decorated_function(*args, **kwargs):
        if not session.get('logged_in'):
            return redirect(url_for('login'))
        return f(*args, **kwargs)
    return decorated_function

def get_db_connection():
    """
    Create and return a database connection with error handling
    """
    try:
        return pymysql.connect(**DB_CONFIG)
    except Exception as e:
        logging.error(f"Database connection failed: {str(e)}")
        return None

@app.route('/')
@login_required
def index():
    """
    Main dashboard page showing recent transactions
    and daily totals
    """
    try:
        conn = get_db_connection()
        if not conn:
            flash('Database connection failed')
            return render_template('error.html')
            
        with conn.cursor() as cursor:
            # Get recent transactions
            cursor.execute("""
                SELECT * FROM transactions 
                ORDER BY created_at DESC 
                LIMIT 50
            """)
            transactions = cursor.fetchall()
            
            # Get today's totals
            cursor.execute("""
                SELECT * FROM daily_totals 
                WHERE date = CURDATE()
            """)
            daily_total = cursor.fetchone()
            
        return render_template(
            'dashboard.html',
            transactions=transactions,
            daily_total=daily_total
        )
        
    except Exception as e:
        logging.error(f"Dashboard error: {str(e)}")
        flash('Error loading dashboard')
        return render_template('error.html')
        
    finally:
        if 'conn' in locals():
            conn.close()

@app.route('/export_csv')
@login_required
def export_csv():
    """
    Generate and download CSV report of transactions
    """
    try:
        conn = get_db_connection()
        if not conn:
            flash('Database connection failed')
            return redirect(url_for('index'))
            
        # Create CSV in memory
        output = StringIO()
        writer = csv.writer(output)
        
        # Write headers
        writer.writerow([
            'ID', 'Date', 'Meals', 'Desserts', 'Entrees',
            'Soups', 'Cookies', 'Rolls', 'Total Price'
        ])
        
        # Get and write transaction data
        with conn.cursor() as cursor:
            cursor.execute("SELECT * FROM transactions ORDER BY created_at")
            for row in cursor.fetchall():
                writer.writerow([
                    row[0], row[9].strftime('%Y-%m-%d'),  # ID and Date
                    row[1], row[2], row[3], row[4],       # Item quantities
                    row[5], row[6], f"${row[7]:.2f}"      # Price
                ])
        
        # Prepare response
        output.seek(0)
        return send_file(
            output,
            mimetype='text/csv',
            as_attachment=True,
            download_name=f'transactions_{datetime.now().strftime("%Y%m%d")}.csv'
        )
        
    except Exception as e:
        logging.error(f"CSV export error: {str(e)}")
        flash('Error exporting data')
        return redirect(url_for('index'))
        
    finally:
        if 'conn' in locals():
            conn.close()

if __name__ == '__main__':
    # Start Flask application
    app.run(
        host='0.0.0.0',  # Listen on all network interfaces
        port=4000,       # Changed to port 4000
        debug=False      # Disable debug mode in production
    )
