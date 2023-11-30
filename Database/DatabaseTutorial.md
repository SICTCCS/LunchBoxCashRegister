# Database (MySQL).
 
 
## Overview
In this tutorial we will set up MySQL on the Raspberry Pi.  MySQL hosts the database used by our web application and accompanying APIs. In this example I am using the Raspberry Pi 3 Model B v1.2. Over the course of the next five lessons, you should have a working database that we can use. And also how to create the luncboxdb and also how to insert into the table.

<br>
 
## Update the Raspberry Pi
Before we begin, we want to ensure the Raspberry Pi is up to date to avoid any package complications. You always want to confirm your Raspberry Pi has all of their packages on the correct versions.


Login to the Raspberry Pi by issuing the ssh command below to establish a new connection.  Make sure to replace `your-raspberry-ip-ip-address` with your Raspberry Pi's IP address.
 
```console
ssh pi@your-raspberry-ip-ip-address
```
The Raspberry Pi will then ask for the password. Enter the Raspberry Pi's default password, <b>raspberry</b>, if you have not changed it yet. You should have a screen similar below.

##### Host terminal
```console
hans@hans:~$ sudo ssh pi@10.0.0.196
pi@10.0.0.196's password: 
Linux raspberrypi 5.10.17-v7+ #1403 SMP Mon Feb 22 11:29:51 GMT 2021 armv7l

The programs included with the Debian GNU/Linux system are free software;
the exact distribution terms for each program are described in the
individual files in /usr/share/doc/*/copyright.

Debian GNU/Linux comes with ABSOLUTELY NO WARRANTY, to the extent
permitted by applicable law.
Last login: Mon Aug 16 22:40:40 2021 from 10.0.0.204
pi@raspberrypi:~ $ 
```
 
To know if you successfully connected to the raspberry pi, just check the username. It should still be <b>pi@raspberrypi</b> like the figure from above. 

<b>Note</b>: There will be times that you connection may close randomly while you are working. The reasons can be a slight disconnection while on wifi, a period of inactivity, and others that can cause you to be closed out on the terminal. The message will be <b>client_loop: disconnected</b>. If this happens, you just need to log back in using the `ssh` command again.


Now that we are connected, issue the commands below to begin the update and upgrade process. The upgrade process can take some time so be patient.

##### Raspberry Pi terminal
```console
sudo apt update
sudo apt upgrade
```

Or if you want to be fancy, you can run both commands on one line

##### Raspberry Pi terminal
```console
pi@raspberrypi:~ $ sudo apt update && sudo apt upgrade
Get:1 http://raspbian.raspberrypi.org/raspbian buster InRelease [15.0 kB]
Get:2 http://archive.raspberrypi.org/debian buster InRelease [32.6 kB]         
Hit:3 https://deb.nodesource.com/node_10.x buster InRelease                    
Get:4 http://raspbian.raspberrypi.org/raspbian buster/main armhf Packages [13.0 MB]
Get:5 http://archive.raspberrypi.org/debian buster/main armhf Packages [378 kB]
Fetched 13.4 MB in 16s (862 kB/s)                                              
Reading package lists... Done
Building dependency tree       
Reading state information... Done
189 packages can be upgraded. Run 'apt list --upgradable' to see them.
Reading package lists... Done
```
<br>

## Install and Configure MySQL

Now that your Raspberry Pi is up to date, you can install MySQL. On your Raspberry Pi terminal, install MySQL server by issuing the following command from your terminal.

##### Raspberry Pi terminal
```console
sudo apt install mariadb-server
```

This will begin your setup for configuring the new software for database application. For the application we're installing, we are going to us <b>MariaDB Server</b>.

MariaDB Server is developed by the same developrs who created MySQL. We're using MariaDB due to it's open-source, scalability, and rich ecosystem that we will take advantage of in later tutorials. Not only will we be allowed to add in records in normal fashion, but we can also use JSON files to push information into our database.

<br>

## Secure the mysql database
You will then need to secure the database. The script we will put in below will give us a way to set a password for the root account. The following command will start the securing process. Install the following script below on your Raspberry Pi terminal. 


##### Raspberry Pi terminal 
```console
sudo mysql_secure_installation
```
You should get a message back for putting in a password below.

##### Raspberry Pi terminal

```console
pi@raspberrypi:~ $ sudo mysql_secure_installation

NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MariaDB
      SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!

In order to log into MariaDB to secure it, we'll need the current
password for the root user.  If you've just installed MariaDB, and
you haven't set the root password yet, the password will be blank,
so you should just press enter here.

Enter current password for root (enter for none): 
```

For this example, we will use <b>Password1</b> as the <b>root</b> level password for the database when we get to that portion. Answering <b>Y</b> to all the prompts ensures the most secure installation. Normally the password would be much stronger than suggested, but for the tutorial and simplicity's sake, just use <b>Password1</b>.
 
<b>Warning</b>: Verify you install both of these packages before you move on to the next section. Failure to do so will lead to errors of creating the database as well as leaving the database unsecured access. The error message will read as so:
<br>

##### Raspberry Pi terminal

```mysql
ERROR 1698 (28000): access denied for user 'localname'@'localhost'
```
<br>

## Login to the database
 
Login to MySQL using root user. You will be prompted with the password we created above. For this example we created the user's password as  <b>Password1</b>. As mentioned above, this password is fragile and suspect to brute-force attacks. When you create a database for production systems, you will want a stronger password.

##### Raspberry Pi terminal

```console
sudo mysql -u root -p
```
You'll have a different text appearance that details you are inside MySQL. On the successful login, you should get the screen below.

##### Raspberry Pi terminal

```console
pi@raspberrypi:~ $ sudo mysql -u root -p
Enter password: 
Welcome to the MariaDB monitor.  Commands end with ; or \g.
Your MariaDB connection id is 54
Server version: 10.3.29-MariaDB-0+deb10u1 Raspbian 10

Copyright (c) 2000, 2018, Oracle, MariaDB Corporation Ab and others.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

MariaDB [(none)]> 
```

This means you are now in the database application, but you still need to make a database in the next section.

<br>
 
## Create lunchbox
 
So now you got in the database, now you need to create the example database. Type the command into your terminal and hit <b>Enter</b>. For our first test case, we are going to create a new database called <b>lunchbox</b>.

##### Raspberry Pi terminal

```mysql
CREATE DATABASE lunchbox;
```
This will result in a query taking place. You should see a similar message below.

##### Raspberry Pi terminal

```mysql
Query OK, 1 row affected
```

<b>Note</b>: Something to keep in mind with MySQL commands. All commands end with the `;`. For a new user, it's easy to forget to hit the semicolon key and just press enter on instinct. If you do by chance hit enter before finishing the command, you will have a little symbol appear at the bottom like so with an unfinished command:
 
```mysql
MariaDB [none]> CREATE USER test01
   ->
```
 
The database is just waiting on you to finish the command. Simply press the <b>;</b> key and the command will either succeed or fail.

Let's break down the command you just ran. Simply put, the Command `CREATE DATABASE` is initializing a new database, which we are calling `lunchbox`. 

In the database, we can begin to build a selection of categories for our data, called <b>Tables</b>. These tables will hold the actual data and have the specific table categories separated by <b>columns</b>. The columns will have specific requirements to fill in for each record that goes into the table. Each record that is created represents a <b>row</b>

So you can think of the sequence as it looks below.

```console
Database -> Tables -> Columns -> Rows
```

Once you have the database, tables, and columns in place, you won't have to change those unless specifications for the records change.

If you want to see if your database has been created, run the command `SHOW DATABASES` to list all the available tables you have created thus far. The new database you created should be listed below.

##### Raspberry Pi terminal

```console
MariaDB [(none)]> CREATE DATABASE lunchbox;
Query OK, 1 row affected (0.001 sec)

MariaDB [(none)]> SHOW Databases;
+--------------------+
| Database           |
+--------------------+
| IoT                |
| lunchbox           |
| information_schema |
| mysql              |
| performance_schema |
+--------------------+
5 rows in set (0.001 sec)

MariaDB [(none)]> 
```

<br>

## Create a user
 
Now that we have a database, we now need a user to interact with it. For this example, the user will be called <b>sictc</b> and the password will be <b>Pencil1</b>.

For example, right now you should be logged into the database as the root user. Run the command `SELECT CURRENT_USER();` and see what populates the window. 

##### Raspberry Pi terminal

```mysql
MariaDB [(none)]> SELECT CURRENT_USER();
+----------------+
| CURRENT_USER() |
+----------------+
| root@localhost |
+----------------+
1 row in set (0.001 sec)

MariaDB [(none)]> 
```
This shows you are currently logged in as the root user. But we shouldn't be logged in as the root user unless needed. Here we will create a new user called `sictc`.

On your terminal, type in the command to create a new user and the associated password on the same line. Reminder that the literal string `localhost` should be typed in, not the ip address corresponding with your Raspberry Pi.

##### Raspberry Pi terminal

```mysql
CREATE USER 'sictc'@'localhost' IDENTIFIED BY 'Pencil1';
```

Just as before, the `CREATE USER` command initiates a new user to add to the system. The username in this case is `sictc`. 

We also add in the parameter that this user can only connect from `localhost`. What that means is, the user must be logged on that computer specifically (your raspberry pi) to have access into the database. Since you are using ssh to login as the local user, you'll be okay. 

The `IDENTIFIED BY` parameter is creating the password that user must type in to gain access into the system. 

The result should create a new user below. You can run the command `SELECT User FROM mysql.user` to see the list of all the current users below.

##### Raspberry Pi terminal

```console
MariaDB [(none)]> CREATE USER 'sictc'@'localhost' IDENTIFIED BY 'Pencil1';
Query OK, 0 rows affected (0.002 sec)

MariaDB [(none)]> SELECT User FROM mysql.user;
+-------+
| User  |
+-------+
| root  |
| sictc |
+-------+
2 rows in set (0.001 sec)

MariaDB [(none)]> 

```
 
<br>

## Granting proper access to our new user
 
Once you have created a new user and their password, that means you have everything you need to log in, right? Not quite. You may have created a new user and password, but you also must allow them to interact with the database. Right now the user is waiting in limbo, void of any actions they can take. 

To correct this, we want to grant all privileges to our new user. Run the command below for the new user you created.

##### Raspberry Pi terminal

```mysql
GRANT ALL PRIVILEGES ON lunchbox.* TO 'sictc'@'localhost';
```

This grants the user <b>sictc</b> the ability to do anything on the database <b>lunchbox</b>. This will allow the user we are creating to manipulate the data for any tables on the database for `lunchbox`. This will allow the user sictc to create, add, alter, delete, update, and more while logged in.

Let's save these changes to our new user. Run the command below to ensure the newly created user can access the database.

##### Raspberry Pi terminal

```mysql
FLUSH PRIVILEGES;
```

So by end of this, you should have run something similar below.

##### Raspberry Pi terminal

```console
MariaDB [(none)]> GRANT ALL PRIVILEGES ON lunchbox.* TO 'sictc'@'localhost';
Query OK, 0 rows affected (0.001 sec)

MariaDB [(none)]> FLUSH PRIVILEGES;
Query OK, 0 rows affected (0.001 sec)

MariaDB [(none)]> 
```

<br> 

## Try out the new user

Now it's time to test out our creation. Let's see if we can login with our new user. First we will need to exit the MySQL console. The console can be exited by issuing the command `quit;` or the keyboard shortcut  `CTRL+D`.
 
Now attempt to login using the new user `sictc`. Remember that the password is `Pencil1`.
 
```console
sudo mysql -u sictc -p
```
You will still have to enter the password, which we made as <b>Pencil1</b>
 
<br>

## Verify MySQL User
We can verify that we are logged in as the sictc user by issuing the following command.
 
```mysql
SELECT CURRENT_USER();
```
Query Results:
```mysql
+-----------------+
| current_user()  |
+-----------------+
| sictc@localhost |
+-----------------+
1 row in set (0.001 sec)
```

<br>

## Create Lunchbox table
This will be the code to create the Lunchbox database table.
 
```mysql
SELECT CURRENT_USER();
```
Query Results:
```
CREATE TABLE mainDatabase( meal int, dessert_side int, entree int, soup int, cookie int, roll int, description varchar(100), date timestamp DEFAULT CURRENT_TIMESTAMP );
```
After that , this will be a random insert to assure your database is working properly. 

```
INSERT INTO mainDatabase (mainMealQuantity, dessertQuantity, entreQuantity, soupQuantity, cookieQuantity, rollQuantity, description) VALUES (1,1,1,1,1,1,"firstorder");
```

Should like something like this after you're done.

<img src="https://github.com/SICTCCS/LunchBoxCashRegister/blob/main/Images/screenshotofdatabase.png" height="500px"
 width="2000px">

<br>
 
## Shortcuts Commands and Cheatsheet
 
| Commands | Definition |
|---|---|
|`ssh pi@ipaddress`| Command to login to Raspberry Pi.|
|`raspberry`| Default password for Raspberry Pi. |
|`touch filename`| Command to create a file. |
|`ls`| Command to list contents in a directory. Think of the contents you see on your desktop icons. |
|`pwd`| Command that prints the current directly, called print working directory|
|`whoami`| Command that displays the current logged in user. |
|`sudo`| Command to allow admin executions, short for `super user do`|
|`apt update`| Command to update repository. |
|`apt upgrade`| Command to upgrade the installed packages. |
|`apt install packagename`| Command to install a package. |
|`arp`| Command displaying the IPv4 network neighbor cache.  |
|`grep`| Command that searches for a pattern matching an expression. |
|`awk`| Command that searches a file or text containing a pattern. |
|`cat`| Display contents of a file, think of it like `call at attention`. |
 
For MySQL
 
| Commands | Definition |
|---|---|
|`sudo mysql -u sictc -p`| Login to MySQL console. |
|`quit;` or `CTRL+D` | Command to exit MySQL console. |
|`lunchbox`| MySQL database. |
|`Password1`| MySQL root password. |
|`sictc`| MySQL exampledb user. |
|`Pencil1`| MySQL exampledb password. |
| `SHOW CURRNET_USER()`| Lists who the current user is logged in, similar to `whoami`.|
| `SELECT User FROM mysql.user`| Display all current users in the system. |
| `CREATE USER 'nameofuser'@'ipaddress' IDENTIFIED by 'passwordname' `| String that creates a user, their address, and password association. |
| `GRANT ALL PRIVILEGES ON databasename.* TO 'nameofuser'@'ipaddress'`| Allow what a user can do on the database. |
| `FLUSH PRIVILEGES`| Save changes for user when they log in next time. | 


### References
 - https://pimylifeup.com/raspberry-pi-mysql/
 - https://www.raspberrypi.org/documentation/remote-access/ssh/
 - https://raspberrytips.com/mac-address-on-raspberry-pi/
 - https://en.wikipedia.org/wiki/MAC_address
 - 
