# Update 1
#### Date: 5 May, 2023
1. I have created `MakeUniDB` class which will, upon running, will create a university database into your prefered database, either *Derby DB* or *MySQL*. 
This class is integrated into `Login` class, which takes user credentials and give asscess to the management system.  
2. `MakeUniDB` class will create a database locally into your device by the name of `UniversityDB`. This is **not** a relational database, however right 
now I am working to make the database more secure and strong by converting it into **relational** and **BCNF**.  
3. I have changed the package structure for the code. Instead of every class in same package, now they are seprated into **4** packages: Student, Instructor, Database and Interface.
4. I have added `CVS Files` which contains randomly generated data to build `UniversityDB` database using `MakeUniDB` class.
