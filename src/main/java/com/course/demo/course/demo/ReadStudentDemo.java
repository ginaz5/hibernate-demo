package com.course.demo.course.demo;

import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FilterOutputStream;

public class ReadStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Student.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // use the session object to save Java object
            System.out.println("Creating new student object...");
            Student tmpStudent = new Student("Read", "Database", "read@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student");
            session.save(tmpStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Saved Student. Generated id: " + tmpStudent.getId());

            // get a new session and transaction and retrieve student: primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Student with id: " + tmpStudent.getId());
            Student myStudent = session.get(Student.class, tmpStudent.getId());
            System.out.println("Get complete: "+ myStudent);
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

}
