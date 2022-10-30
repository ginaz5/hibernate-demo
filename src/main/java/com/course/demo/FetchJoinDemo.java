 package com.course.demo;

import com.course.entity.Course;
import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


 public class FetchJoinDemo {

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

            // start a transaction
            session.beginTransaction();

            // option 2 - Hibernate query with HQL
            int id = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                            + "JOIN FETCH i.courses "
                            + "where i.id=:theInstructorId", Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", id);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("HQL: ==== GET INSTRUCTOR ===" + instructor);

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

            System.out.println("HQL: ==== GET COURSES ===" + instructor.getCourses());
            System.out.println("HQL: Done!");
        }
        finally {
            session.close();
            factory.close();
        }

    }

}
