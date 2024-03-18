package com.ems.serviceimpl;

import com.ems.service.EmployeeService;
import com.ems.entities.Employee;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeServiceImpl implements EmployeeService {

    private SessionFactory sfg;

    // Constructor to initialize Hibernate SessionFactory
    public EmployeeServiceImpl() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        sfg = config.buildSessionFactory();
    }

    // Method to register a new employee
    @Override
    public void registration(String empid, String name, long phoneno, String email, String gender, String address) {
        try (Session session = sfg.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee emp = new Employee();
            emp.setEmpid(empid);
            emp.setFullName(name);
            emp.setPhoneNo(phoneno);
            emp.setEmail(email);
            emp.setGender(gender);
            emp.setAddress(address);
            session.save(emp);
            tx.commit();
            System.out.println("------------------------------------");
            System.out.println("Registration Successful");
        } catch (PersistenceException pe) {
            System.out.println("------------------------------------");
            System.out.println("!! Employee already added !!");
        }
    }

    // Method to update phone number of an employee
    @Override
    public void updatePhoneNo(String empid, long phoneno) {
        try (Session session = sfg.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee emp = session.get(Employee.class, empid);
            if (emp != null) {
                emp.setPhoneNo(phoneno);
                session.update(emp);
                tx.commit();
                System.out.println("Phone No. Updated Successfully");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update email of an employee
    @Override
    public void updateEmail(String empid, String email) {
        try (Session session = sfg.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee emp = session.get(Employee.class, empid);
            if (emp != null) {
                emp.setEmail(email);
                session.update(emp);
                tx.commit();
                System.out.println("Email Updated Successfully");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update address of an employee
    @Override
    public void updateAddress(String empid, String address) {
        try (Session session = sfg.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee emp = session.get(Employee.class, empid);
            if (emp != null) {
                emp.setAddress(address);
                session.update(emp);
                tx.commit();
                System.out.println("Address Updated Successfully");
            } else {
                System.out.println("Employee not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get employee details by ID
    @Override
    public void getEmployeeById(String id) {
        try (Session session = sfg.openSession()) {
            Employee emp = session.load(Employee.class, id);
        
            System.out.println("------------------------------------");
            System.out.println("Employee Id : " + emp.getEmpid());
            System.out.println("Full Name   : " + emp.getFullName());
            System.out.println("Phone Number: " + emp.getPhoneNo());
            System.out.println("Email       : " + emp.getEmail());
            System.out.println("Gender      : " + emp.getGender());
            System.out.println("Address     : " + emp.getAddress());
            System.out.println("------------------------------------");
            
        }catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("------------------------------------");
        	System.out.println("EmployeeID : \"" + id + "\" doesn't exist.");
        }
        
    }

    // Method to get all employees
    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = sfg.openSession()) {
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).getResultList();
            for (Employee emp : employees) {
                getEmployeeById(emp.getEmpid());
            }
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete an employee by ID
    @Override
    public void deleteEmployeeById(String empId) {
        try (Session session = sfg.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                session.delete(employee);
                tx.commit();
                System.out.println("------------------------------------");
                System.out.println("Employee Data Deleted Successfully.");
                System.out.println("------------------------------------");
            } else {
                System.out.println("Deletion was Unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
