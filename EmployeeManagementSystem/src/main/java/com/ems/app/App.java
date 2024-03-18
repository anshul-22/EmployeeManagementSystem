package com.ems.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ems.customexceptions.InvalidNumberException;
import com.ems.operations.AttendanceOperations;
import com.ems.operations.DepartmentOperations;
import com.ems.operations.EmployeeOperations;
import com.ems.operations.ManagerOperations;
import com.ems.operations.ProjectOperations;
import com.ems.operations.SalaryOperations;

public class App {
    
    // Method to print the options menu
    public static void printOptions() {
        System.out.println("------------------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Manage Employees");
        System.out.println("2. Manage Departments");
        System.out.println("3. Manage Projects");
        System.out.println("4. Manage Managers");
        System.out.println("5. Manage Attendance");
        System.out.println("6. Manage Salaries");
        System.out.println("0. Exit");

        System.out.println("------------------------------------");
    }
    
    public static void main(String[] args) throws InvalidNumberException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("------------------------------------");
            System.out.println("-----Employee Management System-----");
            int i=0;
            while (true) {
                printOptions();
                int choice;
                try {
                    // Read user choice from input
                    choice = Integer.parseInt(br.readLine());
                    // Switch based on user choice
                    switch (choice) {
                        case 1:
                            // Call method to manage employees
                            EmployeeOperations.manageEmployees(br);
                            break;
                        case 2:
                            // Call method to manage departments
                            DepartmentOperations.manageDepartments(br);
                            break;
                        case 3:
                            // Call method to manage projects
                            ProjectOperations.manageProjects(br);
                            break;
                        case 4:
                            // Call method to manage managers
                            ManagerOperations.manageManager(br);
                            break;
                        case 5:
                            // Call method to manage attendance
                            AttendanceOperations.manageAttendance(br);
                            break;
                        case 6:
                            // Call method to manage salaries
                            SalaryOperations.manageSalaries(br);
                            break;
                        case 0:
                            // Exit the program
                            System.out.println("==========={Terminated}=============");
                            return;
                        default:
                            // Invalid choice
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid number format
                    System.out.println("Invalid input format. Please enter a number.");
                }
            }
        } catch (IOException e) {
            // Handle IO Exception
            System.out.println("Error occurred while processing input. Exiting program.");
            
        }
    }
}
