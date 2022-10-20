package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForStudentDemo {

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
            Student student = session.get(Student.class, id);

            System.out.println("student: " + student);

            student.getCourses().stream().forEach(course -> System.out.println("course: " + course));

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
