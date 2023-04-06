# Musalasoft
Drones
This application is using an in memory h2 database.

Technologies Used
Java 17
Junit5 and Mockito
Spring 2.7.6

The project consists of 1 scheduled task job that runs every half an hour which logs battery levels to a text file “\batterylog.txt”.

Installation
To build and run the application you need:

JDK 1.8
Maven 3
Running the Application Locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the Application class from your IDE.


I have covered a decent chunk of the functionality, 
With more time, more scope can be covered and there is a vast of improvements which can be done.

Table design is not very great needs improvements.
APIs would need to be secured and well documented.
Deploying the app in a docker container and also have a persistent database setup in a docker container (postgres).
Exceptions may need to be logged into files to enable history tracking.
