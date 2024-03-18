package com.ems.serviceimpl;

import com.ems.entities.Employee;
import com.ems.entities.Salary;
import com.ems.service.SalaryService;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

public class SalaryServiceImpl implements SalaryService {
    // Hibernate configuration setup
    private final Configuration config = new Configuration().configure("hibernate.cfg.xml");
    private final SessionFactory sfg = config.buildSessionFactory();
    private final Session session = sfg.openSession();

    private List<Salary> salaryList;

    public SalaryServiceImpl() {
        this.salaryList = new ArrayList<>();
    }

    @Override
    public void addSalary(String empId, String Sid, double salary, double bonus, double overtime) {
    	try {
	        // Begin transaction
	        session.beginTransaction();
	        
	        // Create new salary object
	        Salary newSalary = new Salary();
	        newSalary.setEmployeeId(empId);
	        newSalary.setSalary(salary);
	        newSalary.setBonus(bonus);
	        newSalary.setOvertime(overtime);
	        newSalary.setSalaryId(Sid);
	
	        // Save the salary and commit transaction
	        session.save(newSalary);
	        session.getTransaction().commit();
    	}
    	catch(ConstraintViolationException cve) {
    		System.out.println("------------------------------------");
    		System.out.println("!! Salary already added !!");
    	}
    	catch(PersistenceException pe) {
    		System.out.println("------------------------------------");;
    		System.out.println("!! Employee does not exist !!");
    	}
    }

    @Override
    public void updateSalary(String empId, double salary, double bonus, double overtime) {
        // Begin transaction
        session.beginTransaction();

        // Retrieve the employee by ID
        Employee employee = session.get(Employee.class, empId);

        // Find the corresponding salary entry
        Salary salaryEntry = session.createQuery("FROM Salary WHERE employee = :employee", Salary.class)
                .setParameter("employee", employee)
                .uniqueResult();

        if (salaryEntry != null) {
            // Update salary entry
            salaryEntry.setSalary(salary);
            salaryEntry.setBonus(bonus);
            salaryEntry.setOvertime(overtime);
        } else {
            // Handle case where salary for employee with empId does not exist
            System.out.println("!! Salary for employee with ID " + empId + " does not exist !!");
        }

        // Commit transaction
        session.getTransaction().commit();
    }
    
    //Delete Salary method
    @Override
    public void removeSalaryById(String empId) {
        // Begin transaction
        session.beginTransaction();

        // Retrieve the employee by ID
        Employee employee = session.get(Employee.class, empId);

        // Find the corresponding salary entry
        Salary salaryEntry = session.createQuery("FROM Salary WHERE employee = :employee", Salary.class)
                .setParameter("employee", employee)
                .uniqueResult();

        if (salaryEntry != null) {
            // Remove salary entry
            session.delete(salaryEntry);
        } else {
            // Handle case where salary for employee with empId does not exist
            System.out.println("Salary for employee with ID " + empId + " does not exist.");
        }

        // Commit transaction
        session.getTransaction().commit();
    }

    @Override
    public void getSalaryByEmpId(String id) {
        // Load the employee by ID
    	try {
	        Salary slry = session.load(Salary.class, id);
	        System.out.println("------------------------------------");
	        System.out.println("Employee Id : " + slry.getEmployeeId());
	        System.out.println("Salary Id   : " + slry.getSalaryId());
	        System.out.println("Salary Amt  : " + slry.getSalary());
	        System.out.println("Bonus       : " + slry.getBonus());
	        System.out.println("Overtime    : " + slry.getOvertime());
	        
	        System.out.println("------------------------------------");
    	}
    	catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("------------------------------------");
        	System.out.println("Salary for EmployeeID : \"" + id + "\" doesn't exist.");
        }
    }


    @Override
    public List<Salary> getAllSalaries() {
        try {
            // Retrieve all employees using HQL
            List<Salary> salary = session.createQuery("FROM Salary", Salary.class).getResultList();
            for (Salary slry : salary) {
            	getSalaryByEmpId(slry.getSalaryId());
            }
            return salary;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or handle the exception as needed
        }
    }
}
