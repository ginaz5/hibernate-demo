package com.course.demo;

import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            // start a transaction
            session.beginTransaction();

            // get instructor by id
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor"+ instructor);

            if(instructor != null)
            {
                System.out.println("Deleting it");
                session.delete(instructor);
            }

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }

    }

}
