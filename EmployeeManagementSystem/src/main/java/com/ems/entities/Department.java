package com.ems.entities;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id",nullable=false)
    private String departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "manager_id",nullable=false)
    private Manager manager;
    
    
    //Getters and Setters
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

}

