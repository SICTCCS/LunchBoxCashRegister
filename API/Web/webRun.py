from flask import Flask, render_template, request, redirect, url_for, flash

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
            return redirect(url_for('data'))  # Redirect to data page
        else:
            flash('Invalid login. Please try again.')
            return render_template('index.html')

    return render_template('index.html')

# Route for the data page (after login)
@app.route('/data')
def data():
    if request.method == 'POST':
        return render_template('index.html')
        
    return render_template('data.html')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=4000, debug=True)
