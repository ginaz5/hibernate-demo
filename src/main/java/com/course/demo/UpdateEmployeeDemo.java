package com.course.demo;

import com.course.entity.Employee;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployeeDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Employee.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            int empId = 2;

            // get a new session and transaction and retrieve student: primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Employee with id: " + empId);
            Employee employee = session.get(Employee.class, empId);

            System.out.println("===> Updating employee ....");
            employee.setFirstname("Scooby");
            session.getTransaction().commit();
            ;
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }

    }

}
