package com.course.demo;

import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Student.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // start a transaction
            session.beginTransaction();

            // Query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // Display the students
            displayStudents(theStudents);

            // with conditions
            theStudents = session.createQuery("from Student s where s.lastname='Doe' ").getResultList();
            System.out.println("\n===>The Last name is Doe");
            displayStudents(theStudents);

            System.out.println("\n===>The student who have last name of Done OR first name Mary");
            theStudents = session.createQuery("from Student s"
                    + " where s.lastname= 'Doe' OR s.firstname='Mary'").getResultList();
            displayStudents(theStudents);

            System.out.println("\n===> Email ends with gmail.com");
            theStudents = session.createQuery("from Student s"
                    + " where s.email like '%gmail.com'").getResultList();
            displayStudents(theStudents);


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student student: theStudents)
        {
            System.out.println(student);
        }
    }

}
