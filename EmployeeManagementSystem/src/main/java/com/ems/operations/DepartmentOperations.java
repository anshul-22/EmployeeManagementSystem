package com.ems.operations;

import com.ems.service.DepartmentService;
import com.ems.serviceimpl.DepartmentServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;

// Operations class for managing departments
public class DepartmentOperations {

    // Method to manage departments
	public static void manageDepartments(BufferedReader br) {
	    try {
	        // Instance of Department Service
	        DepartmentService deptService = new DepartmentServiceImpl();

	        while (true) {
	            // Displaying options
	            System.out.println("------------------------------------");
	            System.out.println("Department Management:");
	            System.out.println("1. Add Department");
	            System.out.println("2. Update Department Name");
	            System.out.println("3. Update Department Location");
	            System.out.println("4. Update Department Manager");
	            System.out.println("5. Delete Department");
	            System.out.println("6. View All Departments");
	            System.out.println("7. Get Department by ID");
	            System.out.println("0. Back");
	            System.out.println("------------------------------------");

	            // Read user input for choice
	            int choice = Integer.parseInt(br.readLine());
	            System.out.println("------------------------------------");
	            // Switch based on user choice
	            switch (choice) {
	                case 0:
	                    return; // Exit the loop and return
	                case 1:
	                    // Add new department
	                    addDepartment(br, deptService);
	                    break;
	                case 2:
	                    // Update department name
	                    updateDepartmentName(br, deptService);
	                    break;
	                case 3:
	                    // Update department location
	                    updateDepartmentLocation(br, deptService);
	                    break;
	                case 4:
	                    // Update department manager
	                    updateDepartmentManager(br, deptService);
	                    break;
	                case 5:
	                    // Delete department
	                    deleteDepartment(br, deptService);
	                    break;
	                case 6:
	                    // View all departments
	                    viewAllDepartments(deptService);
	                    break;
	                case 7:
	                    // Get department by ID
	                    getDepartmentById(br, deptService);
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    } catch (NumberFormatException | IOException e) {
	        System.out.println(e.getMessage());
	    }
	}

	// Other methods remain unchanged


    // Method to add a new department
    private static void addDepartment(BufferedReader br, DepartmentService deptService) throws IOException {

        System.out.println("Enter Department ID: ");
        String id = br.readLine();
        System.out.println("Enter Department Name: ");
        String name = br.readLine();
        System.out.println("Enter Location: ");
        String location = br.readLine();
        System.out.println("Enter Manager ID: ");
        String managerId = br.readLine();
        
        deptService.addDepartment(id, name, location, managerId);
    }

    // Method to update department name
    private static void updateDepartmentName(BufferedReader br, DepartmentService deptService) throws IOException {
        System.out.println("Enter Department ID: ");
        String id = br.readLine();
        System.out.println("Enter New Department Name: ");
        String newName = br.readLine();

        deptService.updateDepartmentName(id, newName);
    }

    // Method to update department location
    private static void updateDepartmentLocation(BufferedReader br, DepartmentService deptService) throws IOException {
        System.out.println("Enter Department ID: ");
        String id = br.readLine();
        System.out.println("Enter New Location: ");
        String newLocation = br.readLine();

        deptService.updateDepartmentLocation(id, newLocation);
    }

    // Method to update department manager
    private static void updateDepartmentManager(BufferedReader br, DepartmentService deptService) throws IOException {
        System.out.println("Enter Department ID: ");
        String id = br.readLine();
        System.out.println("Enter New Manager ID: ");
        String newManagerId = br.readLine();

        deptService.updateDepartmentManager(id, newManagerId);
    }

    // Method to delete a department
    private static void deleteDepartment(BufferedReader br, DepartmentService deptService) throws IOException {
        System.out.println("Enter Department ID: ");
        String id = br.readLine();

        deptService.deleteDepartment(id);
    }

    // Method to view all departments
    private static void viewAllDepartments(DepartmentService deptService) {
        deptService.getAllDepartments();
    }

    // Method to get department by ID
    private static void getDepartmentById(BufferedReader br, DepartmentService deptService) throws IOException {
        System.out.println("Enter Department ID: ");
        String id = br.readLine();

        deptService.getDepartmentById(id);
    }
}
