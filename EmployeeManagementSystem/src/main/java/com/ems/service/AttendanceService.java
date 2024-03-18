package com.ems.service;

import com.ems.entities.Attendance;
import java.util.List;

public interface AttendanceService {
    // Method to mark attendance for an employee
    void markAttendance(String empId, int presentDays, int absentDays);

    // Method to get attendance details for all employees
    List<Attendance> getAllAttendance();

    // Method to delete attendance for an employee
    void deleteAttendance(String empId);

    // Method to add a present day for an employee
    void addPresentDay(String empId);

    // Method to add an absent day for an employee
    void addAbsentDay(String empId);

	void getAttendanceByEmpId(String id);
}
