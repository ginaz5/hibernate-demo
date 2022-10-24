package com.course.demo;

import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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

            // get the object
            int theId = 2;
            InstructorDetail instructorDetail =
                    session.get(InstructorDetail.class, theId);

            System.out.println("Detail ===> " + instructorDetail);

            System.out.println("the associated instructor ===> "
                    + instructorDetail.getInstructor());

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }

    }

}
