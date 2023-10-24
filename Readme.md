# Employee Management Application README


## Features

### User Interface (UI)

#### Login Page
- User login using HTML, CSS, JavaScript.
- Input fields: Username, Password.
- Submit button for authentication.
- Forgot password functionality.
- New user registration link.

#### Registration Page
- User registration using HTML, CSS, JavaScript.
- Input fields: Employee Name, Employee ID, Department, Designation, Technology, Reporting Manager Designation, Reporting Manager.
- Client-side validation for input fields.

### Backend
- Spring Boot/Spring MVC backend using Java (JDK 17).
- Authentication with OAuth 2.0.
- Server-side validation.
- MVC architecture.
- Controller layer handling UI requests.
- Service layer for authentication.
- Repository layer for database interaction.
- Thymleaf

### Database: MySql
- Normalized tables.
- **Employee Table**: Contains Employee details including name, ID, department, designation, technology, and reporting information.
- **Manager Table**: Stores manager information including name, ID, and team associations.
#### ER Diagram

                    +-------------------+
                    |    Employee       |
                    +-------------------+
                    | Employee_ID (PK) |
                    | Employee_Name    |
                    | Department       |
                    | Designation      |
                    | Technology       |
                    | Reporting_Manager_ID (FK) |
                    +-------------------+
                           |
                           |
                           |
                           V
                    +-------------------+
                    |     Manager       |
                    +-------------------+
                    | Manager_ID (PK)  |
                    | Name             |
                    | Teams            |
                    +-------------------+

### Application Server: Tomcat
- Accepts and handles HTTP requests.
- Facilitates communication between the frontend and backend layers.
- Delivers responses to the end user.

## Setup and Deployment

1. **Environment Setup**
    - Ensure you have Java Development Kit (JDK) 17 installed.
    - Set up a suitable Integrated Development Environment (IDE) for Java development (e.g., IntelliJ IDEA, Eclipse).
    - Install and configure Apache Tomcat for serving the application.

2. **Spring Boot Application**
    - Clone the repository containing the Spring Boot application code.
    - Import the project into your IDE.
    - Configure the Mysql database connection in the `application.properties` file.
    - Get the github.client-id & github.client-secret from your GitHub portal----developer settings---Oauth Apps
    - For mail configurations,Enable your 2SV and create APP password from the security page inside the 2VS
    - Build and run the Spring Boot application.

3. **Frontend**
    - Develop or integrate the UI components using HTML, CSS, and JavaScript.
    - Ensure client-side validation for login and registration forms.

4. **Database**
    - Set up an MySql database with normalized tables for Employee and Manager data.
    - Update the database connection details in the Spring Boot application.

5. **Deployment**
    - Deploy the Spring Boot application on the Tomcat server.

6. **Testing**
    - Test the application to ensure proper authentication, registration, and data management.


**Happy Managing Your Employee Data!**