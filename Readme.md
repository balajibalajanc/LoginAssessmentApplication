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
Here's a rephrased version for your README file:


### 1. Set Up Java 17 and MySQL Database

- Start by installing Java 17 on your system.
- Create a MySQL database named "employeedatabase."
- In the application properties, provide the necessary username and password for connecting to your MySQL database.

### 2. Configure OAuth2 Authentication

#### 2.1 GitHub Authentication

- To configure GitHub OAuth2 authentication, follow these steps:
   1. Go to your GitHub portal.
   2. Navigate to "Settings" and then "Developer settings" under "OAuth Apps."
   3. Fill out the form with your desired application name.
   4. Set the homepage URL to "http://localhost:8080."
   5. Specify the Authorization callback URL as "http://localhost:8080/login/oauth2/code/github."
   6. Copy the client ID and secret provided by GitHub into the application properties.
   7. For the keys `spring.security.oauth2.client.registration.google.client-id` and `spring.security.oauth2.client.registration.google.client-secret`, you can use dummy values.

#### 2.2 Email Configuration

- To set up email configuration, configure the following properties:
   - `spring.mail.username`: Provide the Gmail account you will use for the mail server.
   - `service.api.google.username`: Specify the Gmail account from which the token email will be generated.
   - `spring.mail.password`: To obtain this value, enable Two-Step Verification (2SV) for your Gmail account by following [this link](https://support.google.com/accounts/answer/185839). Then, create an app password using [this link](https://support.google.com/accounts/answer/185833), and use the generated app password in your application properties.

### 3. Build the Project

- Use the following command to build the project:
  ```
  mvn clean install
  ```

### Working Images

##### Login Page 
![Alt Text](Utils/Img/Login%20Page.PNG)
##### Registration Page
![Alt Text](Utils/Img/Registration%20Page.PNG)
##### Oauth Page
![Alt Text](Utils/Img/Oauth%20Page.PNG)
##### Forgot Password Page
![Alt Text](Utils/Img/Forgot%20Password%20Page.PNG)
##### Password Reset Page
![Alt Text](Utils/Img/Password%20Reset.PNG)
##### Get Request after Authentication Page
![Alt Text](Utils/Img/Get%20Request%20After%20Authentication.PNG)


**Happy Managing Your Employee Data!**