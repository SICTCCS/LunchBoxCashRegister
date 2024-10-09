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


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=4000, debug=True)
