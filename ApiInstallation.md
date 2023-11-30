## Table of Contents
[Overview](#overview)<br>
[Installing Dependencies](#installdependencies)<br>
[To Run File](#torun)<br>
[To Insert File](#InsertingIndexFile)<br>
[To Run File](#torun)<br>
[Insert Json File](#InsertJsonFile)<br>
[Test index File](#TestIndexFile)<br>
[To Open Web Page](#OpenWebPage)<br>


<div id='overview'/>

## Overview
Instructions to create API to connect Andriod Studio To Database
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

UPLOAD index5.py file which is your api

<div id='torun'/>
To Run API

```console
python3 index.py
```


<div id='InsertJsonFile/>
 
add.json file 

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

To test upload

```console
curl -s -X POST -H "Content-Type: application/json" -d "@json/add_data.json" http://localhost:8000 | jq .
```

<div id='OpenWebPage'/>

To open web page

http://10.60.4.150:8000



NOTES - Whenever download csv file - restart api after