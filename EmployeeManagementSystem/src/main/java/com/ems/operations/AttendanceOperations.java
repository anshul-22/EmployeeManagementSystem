package com.ems.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ems.customexceptions.InvalidNumberException;
import com.ems.service.AttendanceService;
import com.ems.serviceimpl.AttendanceServiceImpl;

public class AttendanceOperations {
	public static void printOptions() {
		 // Displaying options
		 System.out.println("------------------------------------");
	     System.out.println("Attendance Management:");
	     System.out.println("1. Add New Attendance Record");
	     System.out.println("2. Update Attendance");
	     System.out.println("3. Get Attendance Detail of an Employee");
	     System.out.println("4. View Attendance List");
	     System.out.println("5. Delete Attendance");
	     System.out.println("0. Back");
	     System.out.println("------------------------------------");

	}

    public static void manageAttendance(BufferedReader br) throws NumberFormatException, IOException, InvalidNumberException {
        AttendanceService attendanceService = new AttendanceServiceImpl();

        try {
            while (true) {
                printOptions();
            
                int choice = Integer.parseInt(br.readLine());
                System.out.println("------------------------------------");
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                    	System.out.println("------------------------------------");
                        System.out.println("Enter Employee Id: ");
                        String empId = br.readLine();
                        System.out.println("Enter Present Days: ");
                        int presentDays = Integer.parseInt(br.readLine());
                        System.out.println("Enter Absent Days: ");
                        int absentDays = Integer.parseInt(br.readLine());
                        attendanceService.markAttendance(empId, presentDays, absentDays);
                        break;
                    case 2:
                    		System.out.println("1. Add Present Day");
                    		System.out.println("2. Add Absent Day");
                    		System.out.println("0. Back");
                    		// Read user input for choice
                            int ch = Integer.parseInt(br.readLine());
                            System.out.println("------------------------------------");
                            // Switch based on user choice
                            switch (ch) {
                                case 0:
                                    return; // Exit the loop and return
                                case 1:
                                	
                                	System.out.println("Enter Employee Id: ");
                                    String empId2 = br.readLine();
                                    attendanceService.addPresentDay(empId2);
                                    break;
                                case 2:
                                    // Add absent day
                                	System.out.println("Enter Employee Id: ");
                                    String empId3 = br.readLine();
                                	attendanceService.addAbsentDay(empId3);
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        break;
                    case 3: 
                    	System.out.println("Enter Employee Id: ");
                        String empId4 = br.readLine();
                        attendanceService.getAttendanceByEmpId(empId4);;
                        break;
                    case 4:
                    	attendanceService.getAllAttendance();
                    	break;
                    case 5: 
                    	System.out.println("Enter Employee Id: ");
                        String empId5 = br.readLine();
                        attendanceService.deleteAttendance(empId5);
                        break;
                    default: System.out.println("Invalid choice. Please try again.2");
                        
                }
            }
        } catch (NumberFormatException ne) {
            System.out.println(ne);
        } catch (IOException ie) {
            System.out.println(ie);
        }
    }

	
}
