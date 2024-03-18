package com.ems.service;

import com.ems.entities.Manager;

import java.util.List;

public interface ManagerService {
    // Create
    void addManager(String managerID,String mgrname,String title,String email);

    // Read
    void getManagerById(String managerId);
    
    List<Manager> getAllManagers();

    // Update
    void updateManagerTitle(String id,String title);
    
    void updateManagerEmail(String id,String email);
    

    // Delete
    void deleteManager(String managerId);
}
