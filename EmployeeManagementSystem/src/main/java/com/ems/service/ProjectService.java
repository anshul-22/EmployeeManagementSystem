package com.ems.service;

import com.ems.entities.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    // Create
    void addProject(String projectId, String projectName, Date startDate, Date endDate, String status, String managerId);

    // Read
    void getProjectById(String projectId);

    List<Project> getAllProjects();

    // Update
    void updateProjectName(String projectId, String newName);

    void updateProjectStartDate(String projectId, Date newStartDate);

    void updateProjectEndDate(String projectId, Date newEndDate);

    void updateProjectStatus(String projectId, String newStatus);

    void updateProjectManager(String projectId, String newManagerId);

    // Delete
    void deleteProject(String projectId);
    
    //add employee to project
	void addEmployeeToProject(String projectId, String employeeId);
}