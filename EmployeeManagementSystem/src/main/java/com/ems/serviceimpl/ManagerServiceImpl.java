package com.ems.serviceimpl;


import com.ems.entities.Manager;
import com.ems.service.ManagerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class ManagerServiceImpl implements ManagerService {
	
	// Hibernate configuration setup
    Configuration config = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory sfg = config.buildSessionFactory();
    Session session = sfg.openSession();

    @Override
    public void addManager(String managerID, String mgrname, String title, String email) {
        try (Session session = sfg.openSession()) {
            Transaction transaction = session.beginTransaction();

            Manager manager = new Manager();
            manager.setManagerId(managerID);
            manager.setManagerName(mgrname);
            manager.setTitle(title);
            manager.setEmail(email);

            session.save(manager);

            transaction.commit();
        }
    }

    @Override
    public void getManagerById(String managerId) {
        try (Session session = sfg.openSession()) {
            Manager manager = session.get(Manager.class, managerId);
            
            System.out.println("----------------------------");
            System.out.println("Manager Id    : " +manager.getManagerId());
            System.out.println("Manager Name  : "+manager.getManagerName());
            System.out.println("Manager Title : "+manager.getTitle());
            System.out.println("Manager Email : "+manager.getEmail());
        }catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("------------------------------------");
        	System.out.println("EmployeeID : \"" + managerId + "\" doesn't exist.");
        }
    }

    @Override
    public List<Manager> getAllManagers() {
        try (Session session = sfg.openSession()) {
        	// Retrieve all managers using HQL
            List<Manager> managers = session.createQuery("FROM Manager", Manager.class).getResultList();
            for (Manager mgr : managers) {
                getManagerById(mgr.getManagerId());
            }
            return managers;
        }catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }

    @Override
    public void updateManagerTitle(String id, String title) {
        try (Session session = sfg.openSession()) {
            Transaction transaction = session.beginTransaction();

            Manager manager = session.get(Manager.class, id);
            if (manager != null) {
                manager.setTitle(title);
                session.update(manager);
                transaction.commit();
            } else {
                System.out.println("Manager not found with ID: " + id);
            }
        }
    }

    @Override
    public void updateManagerEmail(String id, String email) {
        try (Session session = sfg.openSession()) {
            Transaction transaction = session.beginTransaction();

            Manager manager = session.get(Manager.class, id);
            if (manager != null) {
                manager.setEmail(email);
                session.update(manager);
                transaction.commit();
            } else {
                System.out.println("Manager not found with ID: " + id);
            }
        }
    }

   

    @Override
    public void deleteManager(String managerId) {
        try (Session session = sfg.openSession()) {
            Transaction transaction = session.beginTransaction();

            Manager manager = session.get(Manager.class, managerId);
            if (manager != null) {
                session.delete(manager);
                transaction.commit();
            } else {
                System.out.println("Manager not found with ID: " + managerId);
            }
        }
    }
}
