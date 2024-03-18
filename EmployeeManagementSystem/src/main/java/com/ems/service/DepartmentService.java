package com.ems.service;

import com.ems.entities.Department;

import java.util.List;

public interface DepartmentService {

    // Method to add a new department
    void addDepartment(String departmentId, String departmentName, String location, String managerId);

    // Method to update the name of a department
    void updateDepartmentName(String departmentId, String newName);

    // Method to update the location of a department
    void updateDepartmentLocation(String departmentId, String newLocation);

    // Method to update the manager of a department
    void updateDepartmentManager(String departmentId, String newManagerId);

    // Method to delete a department
    void deleteDepartment(String departmentId);

    // Method to retrieve a department by its ID
    void getDepartmentById(String departmentId);

    // Method to retrieve all departments
    List<Department> getAllDepartments();

}
