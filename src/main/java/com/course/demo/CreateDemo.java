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

            // create the objects
            Instructor instructor =
                    new Instructor("60", "N", "60N@3.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("60N_YOUTUBE", "I love Java");

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
            factory.close();
        }

    }

}
