package com.course.demo;

import com.course.entity.Course;
import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import com.course.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .addAnnotatedClass(Review.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {

            // start a transaction
            session.beginTransaction();

            // get the course
            int id = 10;
            Course course = session.get(Course.class, id);

            // print the course
            System.out.println(course);

            // print the course review
            System.out.println(course.getReviews());

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
