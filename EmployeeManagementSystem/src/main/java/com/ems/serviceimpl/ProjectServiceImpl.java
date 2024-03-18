package com.ems.serviceimpl;

import com.ems.entities.Project;
import com.ems.entities.Employee;
import com.ems.entities.Manager;
import com.ems.service.ProjectService;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;
import java.io.*;

public class ProjectServiceImpl implements ProjectService {
    private final SessionFactory sessionFactory;

    public ProjectServiceImpl() {
        // Initialize Hibernate session factory
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    @Override
    public void addProject(String projectId, String projectName, Date startDate, Date endDate, String status, String managerId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = new Project();
            project.setProjectId(projectId);
            project.setProjectName(projectName);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setStatus(status);

            // Fetch manager by ID and set it to project
            Manager manager = session.get(Manager.class, managerId);
            project.setManager(manager);
            session.save(project);
            transaction.commit();
        }
    }

    @Override
    public void getProjectById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Project project = session.load(Project.class, id);
            if (project != null) {
                System.out.println("------------------------------------");
                System.out.println("Project Id   : " + project.getProjectId());
                System.out.println("Project Name : " + project.getProjectName());
                System.out.println("Start Date   : " + project.getStartDate());
                System.out.println("End Date     : " + project.getEndDate());
                System.out.println("Status       : " + project.getStatus());
                System.out.println("Manager      : " + project.getManager().getManagerName());
                
                // Count of employees associated with the project
                int employeeCount = project.getEmployees().size();
                System.out.println("Employee Count: " + employeeCount);
                
                System.out.println("------------------------------------");
            } else {
                System.out.println("Project not found!");
            }
        }catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("Project with ProjectID : " + id + " doesn't exist.");
        }
    }

    @Override
    public List<Project> getAllProjects() {
        try (Session session = sessionFactory.openSession()) {
            List<Project> projectList=session.createQuery("FROM Project", Project.class).getResultList();
            for(Project proj:projectList) {
            	getProjectById(proj.getProjectId());
            }
            return projectList;
        }
        catch(Exception e) {
        	System.out.println(e);
        	return null;
        }
    }

    @Override
    public void updateProjectName(String projectId, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                project.setProjectName(newName);
                session.update(project);
                transaction.commit();
            }
        }
    }

    @Override
    public void updateProjectStartDate(String projectId, Date newStartDate) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                project.setStartDate(newStartDate);
                session.update(project);
                transaction.commit();
            }
        }
    }

    @Override
    public void updateProjectEndDate(String projectId, Date newEndDate) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                project.setEndDate(newEndDate);
                session.update(project);
                transaction.commit();
            }
        }
    }

    @Override
    public void updateProjectStatus(String projectId, String newStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                project.setStatus(newStatus);
                session.update(project);
                transaction.commit();
            }
        }
    }

    @Override
    public void updateProjectManager(String projectId, String newManagerId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                Manager newManager = session.get(Manager.class, newManagerId);
                project.setManager(newManager);
                session.update(project);
                transaction.commit();
            }
        }
    }

    @Override
    public void deleteProject(String projectId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            if (project != null) {
                session.delete(project);
                transaction.commit();
            }
        }catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("Project with ProjectID : " + projectId + " doesn't exist.");
        }
    }
    
    @Override
    public void addEmployeeToProject(String projectId, String employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Project project = session.get(Project.class, projectId);
            Employee employee = session.get(Employee.class, employeeId);
            System.out.println("------------------------------------");
            
            project.addEmployee(employee);
            session.update(project);
            transaction.commit();
            System.out.println("Employee added to the project successfully.");
        
        }catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("Project or Employee : doesn't exist.");
        }catch(NullPointerException e) {
        	System.out.println("Employee doesn't exist.");
        }
    }
}
