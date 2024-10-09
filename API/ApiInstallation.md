## Table of Contents
[Overview](#overview)<br>
[Installing Dependencies](#installdependencies)<br>
[To Insert File](#InsertingIndexFile)<br>
[To Run File](#torun)<br>
[Insert Json File](#InsertJsonFile)<br>
[Test index File](#TestIndexFile)<br>
[To Open Web Page](#OpenWebPage)<br>


<div id='overview'/>

## Overview
Instructions to create API to connect Andriod Studio To Database.
Before we can do this, we need to install `Python`, Vim, and the required dependencies.
The required dependencies are also called `modules`. Follow the steps below to begin 
preparing your installation of the Node stack. 


<div id='installdependencies'/>

## Installing Properties 
First you will need to install and enable `pip3` on your `Raspberry Pi`.

```console
sudo apt install python3
```
```console
sudo apt install vim
```
```console
sudo apt-get install python3-flask
```
```console
sudo apt install pymysql
```
```console
sudo apt install StringIO
```
```console
sudo apt install curl
```
<div id='InsertingIndexFile'/>

Upload index5.py file which is in the API folder in the github repo. To do this move to a flash drive and move on Pi Documents. 

<div id='torun'/>
To Run API

```console
python3 index.py
```


<div id='InsertJsonFile'/>
 
Create add.json file on raspberry pi Documents. After creating the jason file insert the code below. 

{
	"meal":1,
	"dessert_side":1,
	"entree":1,
	"soup":1,
	"cookie":1,
	"roll":1,
	"description":"test"
}


<div id='TestIndexFile'/>

Run the code below to test to see if any errors accurs. 

```console
curl -s -X POST -H "Content-Type: application/json" -d "@json/add_data.json" http://localhost:8000 | jq .
```

<div id='OpenWebPage'/>

To open the database web page type the code below in your browser. 

http://10.60.4.150:8000

NOTES - Whenever download csv file - restart api after
