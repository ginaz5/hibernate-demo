package com.course.demo;

import com.course.entity.Course;
import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {

            // create the objects
            Instructor instructor =
                    new Instructor("Rose", "BP", "ROSE_BP@3.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("BLACKPINK", "I love BP");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);


            // start a transaction
            session.beginTransaction();

            // this will save details table as well
            session.save(instructor);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }

    }

}
