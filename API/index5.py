from flask import Flask,redirect, Response,render_template, request, jsonify
import pymysql
from io import StringIO




app = Flask(__name__)
# Database configuration
connection = pymysql.connect(
        host='localhost',
        user='api_user',
        password='api_password',
        database='lunchbox'
)
@app.route('/')
def display_data():
    with connection.cursor() as cursor:
        query = "SELECT * FROM mainDatabase"
        cursor.execute(query)
        rows = cursor.fetchall()

    table_html = "<table><tr><th>lunchbox</th><th>score</th></tr>"
    sorted_data_byId = sorted(rows, key=lambda x: x[0])

    userJsonData = []

    for row in sorted_data_byId:
        meal, dessert_side, entree, soup, cookie, roll, description, data, togo = row
        table_row = f"<tr><td>{meal}</td><td>{dessert_side}</td><td>{entree}</td><td>{soup}</td><td>{cookie}</td><td>{roll}</td><td>{description}</td><td>{data}</td><td>{togo}</td></tr>"
        table_html += table_row
        data_dict = {
                'meal': meal,
                'dessert': dessert_side,
                'entree': entree,
                'soup': soup,
                'cookie': cookie,
                'roll': roll,
                'description': description,
                'data': data,
                'togo': togo
            }
        userJsonData.append(data_dict)

    table_html += "</table>"  #method="GET">
    download_button ="""
    <form action="/download_csv"> 
        <button type="submit">Download CSV</button>
    </form>

    """
    return f"{table_html}<br><br>JSON Data: {userJsonData} {download_button} "

@app.route('/add_data', methods=['POST'])
def input_data():
    out="oops something went wrong"
    #mainMealQuantity=1
    #dessertQuantity=1
    #entreQuantity= 1
    #soupQuantity=6
    #cookieQuantity= 3
    #rollQuantity= 8
    #description='testing'
    print(request)
    if request.is_json:
        data = request.get_json()
        meal = data.get("meal")
        dessert_side = data.get("dessert_side")
        entree = data.get("entree")
        soup = data.get("soup")
        cookie = data.get("cookie")
        roll = data.get("roll")
        description = data.get("description")
        togo = data.get("togo")
    #print(mainMeal,dessert,entre,soup,cookie,roll,description)

    #try:
    #    data = request.get_json()
    #    return(data)
    #except:
    #    print(data)
    #    return("didn't work")
    
        

    query=f'INSERT INTO FoodQuantity (meal, dessert_side, entree, soup, cookie, roll, description, togo) VALUES ({meal},{dessert_side},{entree},{soup},{cookie},{roll},"{description}",{togo})'#(%s, %s, %s, %s, %s, %s, %s, %s)"
    #query = f'INSERT INTO mainDatabase (mainMealQuantity, dessertQuantity, entreQuantity, soupQuantity, cookieQuantity, rollQuantity, description) VALUES (%s, %s, %s, %s, %s, %s, %s)'
    print(query)
    try:
        with connection.cursor() as cursor:
            #print(query)
            cursor.execute(query)
            #cursor.execute(query, (mainMealQuantity, dessertQuantity, entreQuantity, soupQuantity, cookieQuantity, rollQuantity, description))
            #print("excuted")
        connection.commit()
        #print("committed")
        out="finished"
    except pymysql.Error as e:
        print(e)
        return out
        #cursor.execute(query, (data['mainMealQuantity'], data['dessertQuantity'], data['entreQuantity'], data['soupQuantity'], data['cookieQuantity'], data['rollQuantity'], data['description'], data['data']))
        #connection.commit()
    #return redirect(url_for('getAll'))
    print("redirecting")
    return "succcess addition!"#redirect('/')
    
@app.route('/download_csv')
def download_csv():
    print("Test")
    with connection.cursor() as cursor:
        query = "SELECT * FROM FoodQuantity"
        cursor.execute(query)
        data = cursor.fetchall()
    print(connection)
    #cursor = connection.cursor()
    #query = "SELECT * FROM FoodQuantity"
    #cursor.execute(query)

    #data = cursor.fetchall()
    csv_data = []
    for row in data:
        csv_data.append(','.join(map(str, row)))
        print(row)
    csv_content = '\n'.join(csv_data)
    print(csv_content)
    response = Response(csv_content, content_type='text/csv')
    response.headers['Content-Disposition'] = 'attachment; filename=data.csv'
    cursor.close()
    connection.close()
    #display_data() 
    print(response)
    print("test")
    return response

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=8000)


