package com.course.demo;

import com.course.entity.Course;
import com.course.entity.Instructor;
import com.course.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            // create some courses
            Course course1 = new Course("Air Guitar");
            Course course2 = new Course("The Pinball MasterClass");

            // add courses to instructor
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            // save courses
            session.save(course1);
            session.save(course2);

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
