package com.course.demo;

import com.course.entity.Course;
import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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

            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("LAZY: ==== GET INSTRUCTOR ===" + instructor);

            // To solve it, call getter method while session open
            System.out.println("LAZY: ==== GET COURSES ===" + instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();

            // close session
            session.close();

            // this should throw exception if you try to get lazy loading data after session closed
            System.out.println("LAZY: ==== GET COURSES ===" + instructor.getCourses());

            System.out.println("LAZY: Done!");
        }
        finally {
            session.close();
            factory.close();
        }

    }

}
