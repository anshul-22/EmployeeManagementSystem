package com.ems.operations;

import java.io.BufferedReader;
import java.io.IOException;
import com.ems.customexceptions.InvalidNumberException;
import com.ems.service.EmployeeService;
import com.ems.serviceimpl.EmployeeServiceImpl;

// Operations class for managing employees
public class EmployeeOperations {

    // Method to manage employees
    public static void manageEmployees(BufferedReader br) {
        // Instance of Employee Service
        EmployeeService emps = new EmployeeServiceImpl();

        try {
            while (true) {
                // Displaying options
                System.out.println("------------------------------------");
                System.out.println("Employee Management:");
                System.out.println("1. Add Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Delete Employee");
                System.out.println("4. View All Employees");
                System.out.println("5. Search Employee");
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
                        // Add new employee
                        addEmployee(br, emps);
                        break;
                    case 2:
                        // Update employee details
                        updateEmployee(br, emps);
                        break;
                    case 3:
                        // Delete employee
                        deleteEmployee(br, emps);
                        break;
                    case 4:
                        // View all employees
                        viewAllEmployees(emps);
                        break;
                    case 5:
                        // Search for employee
                        searchEmployee(br, emps);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException | InvalidNumberException e) {
            System.out.println(e);
        }
    }

    // Method to add a new employee
    private static void addEmployee(BufferedReader br, EmployeeService emps) throws IOException, InvalidNumberException {
        System.out.println("Enter Employee Id: ");
        String id = br.readLine();
        System.out.println("Enter Full Name: ");
        String name = br.readLine();

        System.out.println("Enter Phone No.: ");
        long pno = Long.parseLong(br.readLine());
        if (pno < 999999999 || pno > 9999999999L) {
            throw new InvalidNumberException("!! Phone No should be of 10 digits only !!");
        }
        System.out.println("Enter Email: ");
        String email = br.readLine();
        System.out.println("Enter Gender: ");
        String gender = br.readLine();
        System.out.println("Enter Address: ");
        String address = br.readLine();

        emps.registration(id, name, pno, email, gender, address);
    }

    // Method to update employee details
    private static void updateEmployee(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("1. Update Phone");
        System.out.println("2. Update Email");
        System.out.println("3. Update Address");
        System.out.println("0. Back");
        System.out.println("------------------------------------");
        int choice2 = Integer.parseInt(br.readLine());
        switch (choice2) {
            case 0:
                return; // Go back to main menu
            case 1:
                // Update phone number
                updatePhone(br, emps);
                break;
            case 2:
                // Update email
                updateEmail(br, emps);
                break;
            case 3:
                // Update address
                updateAddress(br, emps);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // Method to update phone number
    private static void updatePhone(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("Enter Employee Id: ");
        String id1 = br.readLine();
        System.out.println("Enter Phone No.: ");
        long newpno = Long.parseLong(br.readLine());

        emps.updatePhoneNo(id1, newpno);
    }

    // Method to update email
    private static void updateEmail(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("Enter Employee Id: ");
        String id2 = br.readLine();
        System.out.println("Enter New Email: ");
        String newemail = br.readLine();

        emps.updateEmail(id2, newemail);
    }

    // Method to update address
    private static void updateAddress(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("Enter Employee Id: ");
        String id3 = br.readLine();
        System.out.println("Enter Address: ");
        String newaddress = br.readLine();

        emps.updateAddress(id3, newaddress);
    }

    // Method to delete an employee
    private static void deleteEmployee(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("Enter Employee Id: ");
        String id4 = br.readLine();
        emps.deleteEmployeeById(id4);
    }

    // Method to view all employees
    private static void viewAllEmployees(EmployeeService emps) {
        emps.getAllEmployees();
    }

    // Method to search for an employee
    private static void searchEmployee(BufferedReader br, EmployeeService emps) throws IOException {
        System.out.println("Enter Employee Id: ");
        String id5 = br.readLine();
        emps.getEmployeeById(id5);
    }
}
