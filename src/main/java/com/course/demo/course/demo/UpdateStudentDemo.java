package com.course.demo.course.demo;

import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(com.course.demo.course.entity.Student.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            int studentId = 1;

            // get a new session and transaction and retrieve student: primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting Student with id: " + studentId);
            com.course.demo.course.entity.Student myStudent = session.get(com.course.demo.course.entity.Student.class, studentId);

            System.out.println("===> Updating student ....");
            myStudent.setFirstname("Scooby");
            session.getTransaction().commit();


            // new transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // update email for all students
            System.out.println("===? update email for all students");
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }

    }

}
