package com.ems.entities;

import javax.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    // Unique identifier for the attendance
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private String attId;

    // Number of present days
    @Column(name = "present_days")
    private int presentDays;

    // Number of absent days
    @Column(name = "absent_days")
    private int absentDays;

    // Reference to the Employee entity using One-to-One mapping
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Constructor
    public Attendance() {}

    // Getters and Setters
    public String getAttendanceId() {
        return attId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attId = attendanceId;
    }

    public int getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(int presentDays) {
        this.presentDays = presentDays;
    }

    public int getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(int absentDays) {
        this.absentDays = absentDays;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Method to get employee ID referenced to employee table
    public String getEmployeeId() {
        if (employee != null) {
            return employee.getEmpid();
        }
        return null;
    }

    // Method to set employee ID referenced to employee table
    public void setEmployeeId(String employeeId) {
        if (employee == null) {
            employee = new Employee();
        }
        employee.setEmpid(employeeId);
    }
}
