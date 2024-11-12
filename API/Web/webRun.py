<<<<<<< HEAD
from flask import Flask, render_template, request, redirect, url_for, flash, session

app = Flask(__name__)
app.secret_key = 'supersecretkey'  # Needed for flash messages

# Dummy user for login authentication
users = {
    "chef": "Sictc1!",
    "admin": "BakerHammonds"
}

# Route for the login page
@app.route('/', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        # Check if the username and password match the dummy user
        if username in users and users[username] == password:
            session['user'] = username  # Store the user in session
            return redirect(url_for('data'))  # Redirect to data page
        else:
            flash('Invalid login. Please try again.')
            return render_template('index.html')

    return render_template('index.html')


# Route for the data page (after login)
@app.route('/data', methods=['GET'])
def data():
    if 'user' in session:  # Ensure the user is logged in
        return render_template('data.html')
    else:
        return redirect(url_for('login'))


# Route for the logout functionality
@app.route('/logout')
def logout():
    session.pop('user', None)  # Remove user from session
    return redirect(url_for('login'))

=======
from flask import Flask, render_template, request, redirect, url_for, flash, session, Response
from io import StringIO
import pymysql
import calendar
import csv
from datetime import datetime

app = Flask(__name__)
app.secret_key = 'supersecretkey'

# Dummy user for login authentication
users = {
    "chef": "Sictc1!",
    "admin": "BakerHammonds"
}

# Connect to the MySQL Database using PyMySQL
def get_db_connection():
    conn = pymysql.connect(
        host="localhost",      # Update with your DB host
        user="api_user",           # Update with your MySQL username
        password="api_password",       # Update with your MySQL password
        database="lunchbox"    # Update with your DB name
    )
    return conn

# Route for the login page
@app.route('/', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        # Check if the username and password match the dummy user
        if username in users and users[username] == password:
            session['user'] = username  # Store the user in session
            return redirect(url_for('data'))  # Redirect to data page
        else:
            flash('Invalid login. Please try again.')
            return render_template('index.html')

    return render_template('index.html')

# Route for the data page (after login)
@app.route('/data', methods=['GET'])
def data():
    if 'user' in session:  # Ensure the user is logged in
        conn = get_db_connection()
        cursor = conn.cursor()

        # Get the month from the query parameter (if present)
        month = request.args.get('month')
        
        # If a month is selected, filter results by that month
        if month:
            # Convert month name to a numeric representation (e.g., January -> 01)
            month_num = list(calendar.month_name).index(month)  # Convert month name to number
            cursor.execute("SELECT meal, dessert_side, entree, soup, cookie, roll, date, togo "
                           "FROM FoodQuantity "
                           "WHERE MONTH(date) = %s "
                           "ORDER BY date DESC", (month_num,))
        else:
            # Fetch the first 30 rows from the FoodQuantity table by default
            cursor.execute("SELECT meal, dessert_side, entree, soup, cookie, roll, date, togo "
                           "FROM FoodQuantity "
                           "ORDER BY date DESC LIMIT 30")

        food_data = cursor.fetchall()
        conn.close()

        # Pass the data to the template
        return render_template('data.html', food_data=food_data)
    else:
        return redirect(url_for('login'))

@app.route('/download_csv', methods=['GET'])
def download_csv():
    if 'user' in session:  # Ensure the user is logged in
        # Get the month and year from the dropdown
        month = request.args.get('month')
        year = request.args.get('year', str(datetime.now().year))  # Default to current year if not provided

        conn = get_db_connection()
        cursor = conn.cursor()
        if month=="None":
            month_num = (datetime.now().month)
        else: 
        # Fetch the data for the selected month
            month_num = list(calendar.month_name).index(month)
        print(month_num)
        if month:
            query = """
            SELECT meal, dessert_side, entree, soup, cookie, roll, date, togo 
            FROM FoodQuantity 
            WHERE MONTH(date) = %s 
            ORDER BY date ASC
            """
            cursor.execute(query, (month_num))
        else:
            query = """
            SELECT meal, dessert_side, entree, soup, cookie, roll, date, togo 
            FROM FoodQuantity 
            ORDER BY date ASC
            LIMIT 30
            """
            cursor.execute(query)

        food_data = cursor.fetchall()
        conn.close()

        # Create a CSV in-memory
        output = StringIO()
        writer = csv.writer(output)

        # Write the header row
        writer.writerow(['Date', 'Meal', 'Dessert', 'Entree', 'Soup', 'Cookie', 'Roll', 'To Go'])

        # Write the data rows
        for row in food_data:
            writer.writerow([row[6], row[0], row[1], row[2], row[3], row[4], row[5], row[7]])

        # Generate the CSV filename based on the selected month and year
        month_num = str(month_num)
        csv_filename = f"{month_num.zfill(2)}-{year[-2:]}.csv"  # Ensure month is 2 digits (e.g., 01 for January)

        # Create a Response object and set the headers
        response = Response(output.getvalue(), mimetype='text/csv')
        response.headers['Content-Disposition'] = f'attachment; filename={csv_filename}'

        return response
    else:
        return redirect(url_for('login'))

# Route for the logout functionality
@app.route('/logout')
def logout():
    session.pop('user', None)  # Remove user from session
    return redirect(url_for('login'))
>>>>>>> ccd8923a5f42e2fc50c743cda7305051de5fbab4

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=4000, debug=True)
