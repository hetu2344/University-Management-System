# University Management System
A desktop based application built on java swing and awt platform, with MySQL database implemented to store values,
process query and show results.  
With features like adding, viewing and updating Student and Instructor records along with an application for abcence as well.  
# Things required before running the project
Since database used is MySQL, you will need the following thigs to run the project:
1. MySQL server which, if you don't have installed in you computer, can be downloaded [here](https://dev.mysql.com/downloads/mysql/).  
2. Few external libraries as follows:  
  i. `my-sql-connector`__*__ to connect to database using **JDBC**(Java Database Connectivity) and run required queries.  
  ii. `jcalander`, an externel library to add calander into system where required.  
  iii. `rs2xml` to convert **ResultSet**, resulted from running queries, into xml to easilty display result into **JTable**.  
  __*__ You will require `derby.jar` if you want your database to be __Derby DB__.  
All of the libraries are available inside jar folder
# Running the code
Attach all three libraries to your project and run `Login.java`.  
There will be only **1** login credentials, i.e., only **1** admin: `username`: admin & `password`: 12345
# Current State
Since the database connected is a local database, if there doesn’t exist any exat database which is used to make the application,
the program might run into error and crash.  
Another issue is that MySQL require a `username` and `password`, to establish connectivity, which can vary from device to device and 
consequently and lead into some errors and crashes.  
## Solution
I am working to make a class that will create the necessary database into the users computer locally and will also assess the issue of 
different username and password for each device by asking for the username and password (**If you have MySQL downloaded**) or 
will use another database called `Apache Derby` which does not require any username and password to eatablish aonnectivity.  
Also, it is upto the user which database they want to use, either `MySQL` or `Apache Derby`.  
**Note**: To develop this kind of class, it will take few days.  
      Once the desired project is completed, I will modify and add comments to code for better understanding.  
# Database
You have two option to select a database, select only **1** from `MySQL` or `Derby DB`. This application is not designed to run *CRUD* operations on both of the databases at the same time.  
## MySQL
If you want your database to be **MySQL**, then you will need my-sql-connector library.  
Since to get access to any database stores in MySQL we need a username and password for the connection. Moreover,this application is based on locally stored database username and password for 
each device will be different, so the application will ask you to provide username and password to connect the database. Also, provide user which have **previlage** to do *CURD* operations in your
MySQL server.
### Initializing the database in MySQL
If you are using the application for first time, then you will have to CREATE the database locally first, which you will be asked at the beginning.  
The database will be stored into localhost:3308, which is default location for hosting databases locally in MySQL.  
The application will also ask for 
# Updates
Any changes regarding source code will be updated as they are made and can be seen in `updates.md` file.
# Cloning repository
To clone this copy the following into terminal:
```
git clone https://github.com/hetu2344/University-Management-System.git
```
## Cloning in VS code
Open command `Command Palette` by shortcut: `cmd + shift + P` and type `Git clone`
select the first option and paste the following: 
```
https://github.com/hetu2344/University-Management-System.git
```
# Notes
Any changes and recommendations regarding this project will be most welcomed.  
Thankyou,  
Het Patel  
