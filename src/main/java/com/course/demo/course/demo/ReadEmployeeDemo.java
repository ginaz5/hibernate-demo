package com.course.demo.course.demo;

import com.course.entity.Employee;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployeeDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // use the session object to save Java object
            System.out.println("Creating new student object...");
            Employee employee = new Employee("alpha", "beta", "READ");

            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

            System.out.println("Saved Student. Generated id: " + employee.getId());

            // get a new session and transaction and retrieve it with primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Student with id: " + employee.getId());
            Employee myEmp = session.get(Employee.class, employee.getId());
            System.out.println("Get complete: "+ myEmp);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

}
