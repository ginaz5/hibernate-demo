package com.course.demo.course.demo;

import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // create 3 student objects
            System.out.println("Creating 3 student objects...");
            Student tmpStudent1 = new Student("John", "Doe", "john@gmail.com");
            Student tmpStudent2 = new Student("Mary", "Public", "mary@gmail.com");
            Student tmpStudent3 = new Student("Bonita", "Applebum", "bonita@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the students");
            session.save(tmpStudent1);
            session.save(tmpStudent2);
            session.save(tmpStudent3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

}
