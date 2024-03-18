package com.ems.service;

import com.ems.entities.Employee; // Importing the Employee entity
import java.util.List; // Importing List interface for return types

public interface EmployeeService {
    // Method to register a new employee
    void registration(String empid, String name, long phoneno, String email, String Gender, String Address);
    
    // Method to update phone number of an employee
    void updatePhoneNo(String empid, long phoneno);
    
    // Method to update email of an employee
    void updateEmail(String empid, String email);
    
    // Method to update address of an employee
    void updateAddress(String empid, String address);
    
    // Method to get details of an employee by ID
    void getEmployeeById(String id);
    
    // Method to get details of all employees
    List<Employee> getAllEmployees();
    
    // Method to delete an employee by ID
    void deleteEmployeeById(String empId);
}
