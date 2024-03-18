package com.ems.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    // Unique identifier for the employee
    @Id
    @Column(name="employee_id")
    private String empid;
    
    // Full name of the employee
    @Column(name="full_name", nullable=false)
    private String fullName;
    
    // Phone number of the employee
    @Column(name="phone_no", nullable=false)
    private long phoneNo;
    
    // Email of the employee
    @Column(name="email", nullable=false)
    private String email;
    
    // Gender of the employee
    @Column(name="gender")
    private String gender;
    
    // Address of the employee
    @Column(name="address")
    private String address;
    
    // Manager associated with the employee
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    // Attendance details of the employee
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Attendance attendance;
    
    // Salary details of the employee
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Salary salary;
    
    // Projects associated with the employee
    @ManyToMany
    private List<Project> projects;

	
	//Getter and Setters
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public Salary getSalary() {
        return salary;
    }
    public void setSalary(Salary salary) {
        this.salary = salary;
    }
    public List<Project> getProjects() {
        return projects;
    }
   
}
