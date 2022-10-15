package com.course.demo.course.demo;

import com.course.entity.Employee;
import com.course.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployeeDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                    .addAnnotatedClass(Employee.class)
                                                    .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try
        {
            int eid = 1;

            // get a new session and transaction and retrieve student: primary key
            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee employee = session.get(Employee.class, eid);

            // delete student
            session.delete(employee);

            // delete student
//            System.out.println("deleting student id = 2");
//            session.createQuery("delete from Student where id=2").executeUpdate();


            session.getTransaction().commit();
            System.out.println("Done!");

        }
        finally {
            factory.close();
        }

    }

}
