from flask import Flask, render_template

app = Flask(__name__)

# Route for the homepage
@app.route('/')
def home():
    return render_template('index.html')  # Assumes you have 'index.html' in a 'templates' folder

if __name__ == '__main__':
    # Run the server on the local network
    app.run(host='0.0.0.0', port=4000, debug=True)

