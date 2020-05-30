# ems
This project implements employee related API's using Springboot,Spring Security and MYSQL.

### Endpoints 

| Method | Endpoint                                                                               | Description                                    | Role   |
|--------|----------------------------------------------------------------------------------------|------------------------------------------------|--------|
| GET    | /employees                                                                             | Get all the employees from database            | Anyone |
| GET    | /employees/{id}                                                                        | Get Specific employee                          | Anyone |
| POST   | /employees                                                                             | Create new Employee                            | ADMIN  |
| PUT    | /employees/{id}                                                                        | Update specific employee                       | ADMIN  |
| DELETE | /employees/{id}                                                                        | Delete specific employee                       | ADMIN  |
| POST   | /auth/token                                                                            | Get JWT token by passing username and password | Anyone |
| GET    | /employees?name={name}                                                                 | Filter Employees by First Name                 | Anyone |
| GET    | /employees?page={page_number}&limit={page size} &sortKey={sortby}&sortOrder={asc,desc} | Paging and Sorting capabilities                | Anyone |

### Usage
- Git clone the code
- Update the application.properties with database credentials and ddl-update as create.
- Run the application.

### Technologies Used
- Spring boot
- Spring Security
- MySQL
- Postman Client
- Git
- JWT
- Embedded Tomcat


