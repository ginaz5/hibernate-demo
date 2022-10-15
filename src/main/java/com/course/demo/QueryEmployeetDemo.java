package com.course.demo;

import com.course.entity.Employee;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryEmployeetDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Employee.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // start a transaction
            session.beginTransaction();

            // Query students
            List<Employee> theEmployees = session.createQuery("from Employee").getResultList();

            // Display the students
            System.out.println("==== the epmloyees ===");
            displayStudents(theEmployees);

            // with conditions
            theEmployees = session.createQuery("from Employee e where e.lastname='Deep' ").getResultList();
            System.out.println("\n===>The Last name is Deep");
            displayStudents(theEmployees);

            System.out.println("\n===> Depp or Amy");
            theEmployees = session.createQuery("from Employee e"
                    + " where e.lastname= 'Deep' OR e.firstname='Amy'").getResultList();
            displayStudents(theEmployees);

            System.out.println("\n===> prefix A");
            theEmployees = session.createQuery("from Employee e"
                    + " where e.company like 'A%'").getResultList();
            displayStudents(theEmployees);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Employee> theEmployees) {
        for (Employee employee: theEmployees)
        {
            System.out.println(employee);
        }
    }

}
