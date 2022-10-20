package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCourserForMaryDemo {

    public static void main(String[] args) {

        //sql do zapisywania polskich znaków w bazie:
        //ALTER TABLE student CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //get Mary from database
            int id = 3;
            Student maryStudent = session.get(Student.class, id);

            //create courses
            Course course = new Course("Helping 101");
            Course course2 = new Course("Looking 101");

            //add Mary to courses
            course.addStudent(maryStudent);
            course2.addStudent(maryStudent);

            session.save(course);
            session.save(course2);

            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
