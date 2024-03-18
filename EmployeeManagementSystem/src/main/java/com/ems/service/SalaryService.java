package com.ems.service;

import com.ems.entities.Salary;
import java.util.List;

public interface SalaryService {
    // Add a new salary entry for the employee with the given ID
    void addSalary(String empId,String Sid, double salary, double bonus, double overtime);
    
    // Update an existing salary entry for the employee with the given ID
    void updateSalary(String empId, double salary, double bonus, double overtime);
    
    // Remove the salary entry for the employee with the given ID
    void removeSalaryById(String empId);
    
    // Retrieve the salary entry for the employee with the given ID
    void getSalaryByEmpId(String empId);
    
    // Retrieve all salary entries for all employees
    List<Salary> getAllSalaries();
}
