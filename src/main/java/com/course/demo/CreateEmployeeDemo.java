package com.course.demo;

import com.course.entity.Employee;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateEmployeeDemo {

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
            Employee employee = new Employee("Tom", "Cruise", "Apple");
            Employee employee2 = new Employee("Amy", "Heard", "Amazon");
            Employee employee3 = new Employee("John", "Deep", "Facebook");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the employee");
            session.save(employee);
            session.save(employee2);
            session.save(employee3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

}
