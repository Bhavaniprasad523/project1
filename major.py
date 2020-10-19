from flask import Flask,render_template,request,redirect,url_for,g,session
import mysql.connector
import pickle
import pandas as pd
import numpy as np
import os
import random


test = pd.read_csv('newtest.csv')

model = pickle.load(open('svm_hotel_new.sav','rb'))

mydb = mysql.connector.connect(host ="localhost",user="root",passwd="6393",database="hotel_prediction")

mycursor = mydb.cursor()

app = Flask(__name__)
app.secret_key = os.urandom(24)

@app.before_request
def before_request():
    g.cid = None

@app.route("/",methods=['GET','POST'])
def login():
    user_list = []
    error = None
    if request.method == 'POST':
      mycursor.execute("select * from users")
      result = mycursor.fetchall()
      for user in result:
          if user[1]!= request.form['username'] or user[2] != request.form['password']:
              error = "Invalid username or password"
          else:
              session['user'] = user[1]
              return redirect(url_for('home'))
    return render_template('login.html',error=error)


@app.route("/home",methods=['GET','POST'])
def home():
    hotel_list = []
    mycursor.execute("select userid from users where username='venkat'")
    result = mycursor.fetchall()
    px = test[test.user_id==result[0][0]].sample(1)
    pxd  = px.drop(['user_id','hotel_cluster'],axis=1)
    hc = model.predict(pxd)
    hotel = hc[0]
    #return str(hotel)
    mycursor.execute("select * from hotel_clusters where clusterid="+str(hc[0]))
    result = mycursor.fetchall()
    for hotel in result:
        hotel_list.append(hotel[1])
    return render_template('home.html',hotel=hotel_list,no_of_hotels=len(hotel_list))
@app.route("/signup",methods=['GET','POST'])
def signup():
    if request.method == 'POST':
        sql = "insert into new_user (username,email,password,phoneno,hotel) values (%s,%s,%s,%s,%s)"
        val = (request.form['username'],request.form['email'],request.form['password'],request.form['phoneno'],g.cid)
        mycursor.execute(sql,val)
        mydb.commit()
        return redirect(url_for('randomhotel'))
    return render_template('signup.html')
@app.route("/randomhotel",methods=['GET','POST'])
def randomhotel():
    if request.method == 'POST':
        hotel_list = []
        mycursor.execute("select * from hotel_clusters where clusterid=1")
        result = mycursor.fetchall()
        for hotel in result:
            hotel_list.append(hotel[1])
        sql = "insert into features (checkin,checkout,noofadults,nofchildren,noofrooms,count) values (%s,%s,%s,%s,%s,%s)"
        val = (request.form['checkin'],request.form['checkout'],request.form['noofadults'],request.form['noofchildren'],request.form['noofrooms'],request.form['count'])
        mycursor.execute(sql,val)
        mydb.commit()
        return render_template('home.html',hotel=hotel_list,no_of_hotels=len(hotel_list))
    return render_template('user.html')
if __name__ == "__main__":
    app.run(debug=True)
