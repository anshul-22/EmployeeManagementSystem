package com.ems.serviceimpl;

import com.ems.entities.Attendance;
import com.ems.entities.Employee;
import com.ems.service.AttendanceService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {

    // Hibernate configuration setup
    private final Configuration config = new Configuration().configure("hibernate.cfg.xml");
    private final SessionFactory sfg = config.buildSessionFactory();
    private final Session session = sfg.openSession();

    @Override
    public void markAttendance(String empId, int presentDays, int absentDays) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Retrieve the employee by ID
            Employee employee = session.get(Employee.class, empId);
            System.out.println("------------------------------------");
            if (employee != null) {
                Attendance attendance = new Attendance();
                attendance.setEmployee(employee);
                attendance.setPresentDays(presentDays);
                attendance.setAbsentDays(absentDays);

                session.save(attendance);
                tx.commit();
                System.out.println("Attendance added successfully.");
            } else {
                System.out.println("!! Employee with ID: " + empId+" does not exist !!");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void addPresentDay(String empId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("------------------------------------");
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                Attendance attendance = getOrCreateAttendanceForEmployee(employee);
                attendance.setPresentDays(attendance.getPresentDays() + 1);
                session.update(attendance);
                tx.commit();
                System.out.println("PresentDays Updated Successfully.");
            } else {
                System.out.println("Employee with ID " + empId + " does not exist.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void addAbsentDay(String empId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("------------------------------------");
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                Attendance attendance = getOrCreateAttendanceForEmployee(employee);
                attendance.setAbsentDays(attendance.getAbsentDays() + 1);
                session.update(attendance);
                tx.commit();
                System.out.println("AbsentDays Updated Successfully.");
            } else {
                System.out.println("Employee with ID " + empId + " does not exist.");
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private Attendance getOrCreateAttendanceForEmployee(Employee employee) {
        Attendance attendance = employee.getAttendance();
        if (attendance == null) {
            attendance = new Attendance();
            attendance.setEmployee(employee);
            session.save(attendance);
            employee.setAttendance(attendance);
            session.update(employee);
        }
        return attendance;
    }

    @Override
    public void getAttendanceByEmpId(String empid) {
        try {
            Attendance attendance = session.createQuery("FROM Attendance WHERE employee.empid = :empid", Attendance.class)
                    .setParameter("empid", empid)
                    .uniqueResult();
            System.out.println("Attendance Details:-");
            System.out.println("Employee ID   : " + empid);
            System.out.println("Attendance Id : " + attendance.getAttendanceId());
            System.out.println("Present Days  : " + attendance.getPresentDays());
            System.out.println("Absent Days   : " + attendance.getAbsentDays());
        
        } catch(org.hibernate.ObjectNotFoundException e) {
        	System.out.println("Attendance for EmployeeID: \"" + empid + "\" doesn't exist.");
        }
    }

    public List<Attendance> getAllAttendance() {
        try {
            List<Attendance> attendanceList = session.createQuery("FROM Attendance", Attendance.class).getResultList();
            for (Attendance attendance : attendanceList) {
                getAttendanceByEmpId(attendance.getEmployee().getEmpid());
            }
            return attendanceList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void deleteAttendance(String empId) {
        Transaction tx = null;
        try {
            // Begin transaction
            tx = session.beginTransaction();

            // Load the employee by ID
            Employee employee = session.get(Employee.class, empId);

            // Ensure that the employee exists
            if (employee != null) {
                // Retrieve the attendance record for the employee
                Attendance attendance = employee.getAttendance();
                System.out.println("------------------------------------");
                // Check if attendance record exists
                if (attendance != null) {
                    // Remove the attendance record
                    session.delete(attendance);
                    System.out.println("Attendance records deleted for Employee with ID: " + empId);
                } else {
                    System.out.println("No attendance records found for Employee with ID: " + empId);
                }
            } else {
                System.out.println("Employee with ID " + empId + " does not exist.");
            }

            // Commit transaction
            tx.commit();
        } catch (Exception e) {
            // Rollback transaction if an exception occurs
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
