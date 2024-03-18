package com.ems.operations;

import com.ems.entities.Salary;
import com.ems.service.SalaryService;
import com.ems.serviceimpl.SalaryServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalaryOperations {
	public static void printOptions() {
		// Displaying options
        System.out.println("------------------------------------");
        System.out.println("Salary Management:");
        System.out.println("1. Add Salary");
        System.out.println("2. Update Salary");
        System.out.println("3. Get Salary of an Employee");
        System.out.println("4. View All Salaries");
        System.out.println("5. Delete Salary");
        System.out.println("0. Back");
        System.out.println("------------------------------------");

	}
    // Method to manage salaries
    public static void manageSalaries(BufferedReader br) throws IOException {
       
        SalaryService salaryService = new SalaryServiceImpl();

        try {
            while (true) {
                printOptions();
                // Read user input for choice
                int choice = Integer.parseInt(br.readLine());

                // Switch based on user choice
                switch (choice) {
                    case 0:
                        return; // Exit the loop and return
                    case 1:
                        // Add new salary
                        System.out.println("Enter Employee Id: ");
                        String empId = br.readLine();
                        System.out.println("Enter Salaray Id: ");
                        String sid = br.readLine();
                        System.out.println("Enter Salary: ");
                        double salary = Double.parseDouble(br.readLine());
                        System.out.println("Enter Bonus: ");
                        double bonus = Double.parseDouble(br.readLine());
                        System.out.println("Enter Overtime: ");
                        double overtime = Double.parseDouble(br.readLine());

                        // Call service method to add new salary
                        salaryService.addSalary(empId,sid, salary, bonus, overtime);
                        break;
                    case 2:
                        // Update salary
                        System.out.println("Enter Employee Id: ");
                        String updateId = br.readLine();
                        System.out.println("Enter Salary: ");
                        double updatedSalary = Double.parseDouble(br.readLine());
                        System.out.println("Enter Bonus: ");
                        double updatedBonus = Double.parseDouble(br.readLine());
                        System.out.println("Enter Overtime: ");
                        double updatedOvertime = Double.parseDouble(br.readLine());

                        // Call service method to update salary
                        salaryService.updateSalary(updateId, updatedSalary, updatedBonus, updatedOvertime);
                        break;
                    case 3:
                    	System.out.println("Enter Employee Id: ");
                        String empId2 = br.readLine();
                        salaryService.getSalaryByEmpId(empId2);
                        break;
                    case 4:
                        // View all salaries
                        System.out.println("------------------------------------");
                        System.out.println("All Salaries:");
                        for (Salary salaryEntry : salaryService.getAllSalaries()) {
                            System.out.println(salaryEntry);
                        }
                        break;
                    case 5:
                        // Delete salary
                        System.out.println("Enter Employee Id: ");
                        String deleteId = br.readLine();
                        // Call service method to delete salary
                        salaryService.removeSalaryById(deleteId);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (NumberFormatException | IOException e) {
            // Handle number format exception and IO exception
            System.out.println(e);
        }
    }
}
