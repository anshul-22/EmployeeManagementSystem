package com.ems.serviceimpl;

import com.ems.entities.Department;
import com.ems.entities.Manager;
import com.ems.service.DepartmentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final SessionFactory sessionFactory;

    public DepartmentServiceImpl() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    @Override
    public void addDepartment(String departmentId, String departmentName, String location, String managerId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = new Department();
            department.setDepartmentId(departmentId);
            department.setDepartmentName(departmentName);
            department.setLocation(location);

            Manager manager = session.get(Manager.class, managerId);
            department.setManager(manager);

            session.save(department);
            transaction.commit();
        }
    }

    @Override
    public void updateDepartmentName(String departmentId, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = session.get(Department.class, departmentId);
            department.setDepartmentName(newName);

            session.update(department);
            transaction.commit();
        }
    }

    @Override
    public void updateDepartmentLocation(String departmentId, String newLocation) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = session.get(Department.class, departmentId);
            department.setLocation(newLocation);

            session.update(department);
            transaction.commit();
        }
    }

    @Override
    public void updateDepartmentManager(String departmentId, String newManagerId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = session.get(Department.class, departmentId);
            Manager manager = session.get(Manager.class, newManagerId);
            department.setManager(manager);

            session.update(department);
            transaction.commit();
        }
    }

    @Override
    public void deleteDepartment(String departmentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Department department = session.get(Department.class, departmentId);
            session.delete(department);

            transaction.commit();
        }
    }

    @Override
    public void getDepartmentById(String departmentId) {
        try (Session session = sessionFactory.openSession()) {
            Department dept=session.load(Department.class, departmentId);
            System.out.println("------------------------------------");
            System.out.println("Department Id      : " + dept.getDepartmentId());
            System.out.println("Department Name    : " + dept.getDepartmentName());
            System.out.println("Department Location: " + dept.getLocation());
            System.out.println("Manager Id         : "+dept.getManager().getManagerId());
            System.out.println("------------------------------------");
        }
        
    }
    

    @Override
    public List<Department> getAllDepartments() {
        try (Session session = sessionFactory.openSession()) {
        	List<Department> departments = session.createQuery("FROM Department", Department.class).getResultList();
        	for (Department dept : departments) {
        		getDepartmentById(dept.getDepartmentId());
            }
        	return departments;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
     }
}
