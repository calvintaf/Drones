#Musalasoft
##Drones
This application uses an in-memory H2 database to perform its functionalities.

#Technologies Used
Java 17
Junit5 and Mockito
Spring 2.7.6
#About
The project consists of one scheduled task that runs every half an hour, logging battery levels to a text file named "batterylog.txt".

#Installation
To build and run the application, you will need:

JDK 1.8
Maven 3
Running the Application Locally
There are several ways to run a Spring Boot application on your local machine.

One option is to execute the main method in the Application class from your IDE.

#Future Improvements
Although the application covers a decent amount of functionality, there is still scope for improvement. Some future upgrades could include:

Improvements to the table design
Securing and properly documenting APIs
Dockerizing the application and setting up a persistent database such as Postgres
Logging exceptions in files to enable for history tracking.