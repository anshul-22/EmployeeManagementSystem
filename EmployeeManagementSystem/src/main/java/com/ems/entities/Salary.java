package com.ems.entities;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    // Unique identifier for the salary
    @Id
    @Column(name = "salary_id")
    private String salaryId;

    // Base salary of the employee
    @Column(name = "salary")
    private double salary;

    // Bonus amount for the employee
    @Column(name = "bonus")
    private double bonus;

    // Overtime amount for the employee
    @Column(name = "overtime")
    private double overtime;

    // Reference to the Employee entity using One-to-One mapping
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    // Getters and Setters
    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
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

