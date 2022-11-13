package com.course.demo;

import com.course.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Instructor.class)
                                                    .addAnnotatedClass(InstructorDetail.class)
                                                    .addAnnotatedClass(Course.class)
                                                    .addAnnotatedClass(Review.class)
                                                    .addAnnotatedClass(Student.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {

            // start a transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("QQQ");

            // save the course
            System.out.println("Saving the course...");
            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);

            // create students
            Student student = new Student("TEST", "Join", "terramiss@lovecode.com");


            // add students to the course
//            course.addStudent(student);
//            course.addStudent(student2);

            student.addCourse(course);

            // save the students
            System.out.println("Saving students...");
            session.save(student);

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
