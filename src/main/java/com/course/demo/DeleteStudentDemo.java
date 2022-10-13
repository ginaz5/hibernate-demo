package com.course.demo;

import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Student.class)
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
            Student myStudent = session.get(Student.class, studentId);

            // delete student
            System.out.println("deleting student " + myStudent);
            session.delete(myStudent);

            // delete student
            System.out.println("deleting student id = 2");
            session.createQuery("delete from Student where id=2").executeUpdate();


            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }

    }

}
