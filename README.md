# EmployeeManagementSystem
Employee Management System built using Java, Hibernate and MySql.

A Java-based Employee Management System (EMS) project using MySQL as the backend database is a comprehensive solution for managing various aspects of employee data and processes within an organization. 

## Project Overview:
The Employee Management System is a console-based application developed using Java for the backend logic
and MySQL for data storage. 
It provides functionalities for HR administrators, managers, and employees to manage employee-related information efficiently.

## Details:

1. Database Management:
Utilizes MySQL as the RDBMS to store data for six entities: Employee, Manager, Project, Department, Attendance, and Salary.
Tables are designed to maintain relationships between entities, ensuring data integrity and efficient querying.

2. Hibernate ORM:
Utilizes Hibernate as the ORM framework for mapping Java objects to database tables and vice versa.
Simplifies database interactions by abstracting SQL queries and managing database connections and transactions.
Java Backend:
Implements business logic, data validation, and application flow control using Java.
Leverages Hibernate for CRUD operations on entities, ensuring consistency and reliability.

3. Entities:

      - Employee: Represents individual employees, storing details such as name, contact information, department, etc.
      
      - Manager: Represents managers overseeing employees, with attributes including name, department, etc.
      
      - Project: Represents projects assigned to employees, with attributes like name, description, start date, end date, etc.
      
      - Department: Represents organizational departments, storing details such as name, location, etc.
      
      - Attendance: Tracks employee attendance records, including presentDays, absentDays, etc.
      
      - Salary: Stores salary information for employees, including salary amount, payment date, etc.

4. CRUD Operations:
Supports CRUD operations for all entities, enabling the creation, retrieval, updating, and deletion of records.
Each entity's CRUD operations are interconnected, allowing seamless management of related data across the system.

5. User Interface:
The system UI is console based.User can easily interact with the application using this UI. It has been designed for a simple and convenient usage.

7. User: 
The system is designed for the administrators only not for the employees.

## Synopsis
[Download Synopsis PDF](synopsis%20ems.pdf)

## Datase Design
[Download PDF Document](DBdesign.pdf)

## Presentation
[Download PPTX Document](Employee%20Management.pptx)


