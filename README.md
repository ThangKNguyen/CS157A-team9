
## Setup Instructions

### Prerequisites
- Java Development Kit (JDK).
- Apache Tomcat server installed and configured.
- MySQL server installed and running.
- IDE (e.g., IntelliJ, Eclipse, or NetBeans).

### Code Setup
1. Clone this repository to your local machine.
2. Import the project into your preferred IDE.

### Database Setup
1. Open the `/sql` folder and locate the SQL file for database schema setup.
2. Create a MySQL database named `LibTrack`.
3. Import the SQL file into the `LibTrack` database using your MySQL client or command line.


### Configuration
The database connection details are in java/com/LibTrack/utils and its name is `DatabaseConn.java`, update it as needed for your own databases:

```java
private static String dburl = "jdbc:mysql://localhost:3306/LibTrack";
private static String dbuname = "root";
private static String dbpassword = "CS157A@SJSU"; // Update as needed
```

### Starting the application
- In your IDE, start your Tomcat server and run the project on the server.
- Navigate to: http://localhost:8080/CS157A-team9/landingPage.jsp
- Click on log in to log in as one of the users in the database or register as a new user. 


