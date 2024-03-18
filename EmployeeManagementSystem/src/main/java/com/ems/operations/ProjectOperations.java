package com.ems.operations;

import com.ems.service.ProjectService;
import com.ems.serviceimpl.ProjectServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectOperations {
	public static void printOptions() {
		// Displaying options
        System.out.println("------------------------------------");
        System.out.println("Project Management:");
        System.out.println("1. Add Project");
        System.out.println("2. Update Project");
        System.out.println("3. Delete Project");
        System.out.println("4. Add an Employee to Project");
        System.out.println("5. Get Project Detail by ID");
        System.out.println("6. View All Projects");
        System.out.println("0. Back");
        System.out.println("------------------------------------");

	}
    // Method to manage projects
    public static void manageProjects(BufferedReader br) {
        try {
            // Instance of Project Service
            ProjectService projectService = new ProjectServiceImpl();

            while (true) {
                printOptions();
                // Read user input for choice
                int choice = Integer.parseInt(br.readLine());
                System.out.println("------------------------------------");
                // Switch based on user choice
                switch (choice) {
                    case 0:
                        return; // Exit the loop and return
                    case 1:
                        // Add new project
                        addProject(br, projectService);
                        break;
                    case 2:
                        // Update project details
                        updateProject(br, projectService);
                        break;
                    case 3:
                        // Delete project
                        deleteProject(br, projectService);
                        break;
                    case 4:
                    	//Add Employee to Project
                    	addEmployeeToProject(br,projectService);
                    	break;
                    case 5:
                    	// Get Project by ProjectID
                    	getProjectById(br,projectService);
                    	break;
                    case 6:
                        // View all projects
                        viewAllProjects(projectService);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (NumberFormatException | IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEmployeeToProject(BufferedReader br, ProjectService projectService) throws IOException {
		System.out.println("Enter Project Id: ");
		String projId=br.readLine();
		System.out.println("Enter Employee Id: ");
		String empId=br.readLine();
		projectService.addEmployeeToProject(projId, empId);
		
	}
	// Method to add a new project
    private static void addProject(BufferedReader br, ProjectService projectService) throws IOException, ParseException {
        System.out.println("------------------------------------");
        System.out.println("Enter Project Id: ");
        String projectId = br.readLine();
        System.out.println("Enter Project Name: ");
        String projectName = br.readLine();
        System.out.println("Enter Start Date (yyyy-MM-dd): ");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(br.readLine());
        System.out.println("Enter End Date (yyyy-MM-dd): ");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(br.readLine());
        System.out.println("Enter Status: ");
        String status = br.readLine();
        System.out.println("Enter Manager Id: ");
        String managerId = br.readLine();

        projectService.addProject(projectId, projectName, startDate, endDate, status, managerId);
    }

    // Method to update project details
    private static void updateProject(BufferedReader br, ProjectService projectService) throws IOException, ParseException {
        System.out.println("Enter Project Id to update: ");
        String projectId = br.readLine();
        System.out.println("1. Update Project Name");
        System.out.println("2. Update Start Date");
        System.out.println("3. Update End Date");
        System.out.println("4. Update Status");
        System.out.println("5. Update Manager Id");
        System.out.println("0. Back");
        int choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 0:
                return; // Go back to main menu
            case 1:
                // Update project name
                System.out.println("Enter New Project Name: ");
                String newName = br.readLine();
                projectService.updateProjectName(projectId, newName);
                break;
            case 2:
                // Update start date
                System.out.println("Enter New Start Date (yyyy-MM-dd): ");
                Date newStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(br.readLine());
                projectService.updateProjectStartDate(projectId, newStartDate);
                break;
            case 3:
                // Update end date
                System.out.println("Enter New End Date (YYYY-MM-DD): ");
                Date newEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(br.readLine());
                projectService.updateProjectEndDate(projectId, newEndDate);
                break;
            case 4:
                // Update status
                System.out.println("Enter New Status: ");
                String newStatus = br.readLine();
                projectService.updateProjectStatus(projectId, newStatus);
                break;
            case 5:
                // Update manager id
                System.out.println("Enter New Manager Id: ");
                String newManagerId = br.readLine();
                projectService.updateProjectManager(projectId, newManagerId);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to delete a project
    private static void deleteProject(BufferedReader br, ProjectService projectService) throws IOException {
        System.out.println("Enter Project Id to delete: ");
        String projectId = br.readLine();
        projectService.deleteProject(projectId);
    }

    // Method to view all projects
    private static void viewAllProjects(ProjectService projectService) {
        projectService.getAllProjects().forEach(System.out::println);
    }
    
    //Method to get Project details with ID
    private static void getProjectById(BufferedReader br,ProjectService projectService) throws IOException {
    	System.out.println("Enter Project Id: ");
    	String pid=br.readLine();
    	projectService.getProjectById(pid);
    }
}
