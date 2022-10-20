package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

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

            Course course = new Course("Shouting 101");
            session.save(course);

            Student student = new Student("Mary", "Poppins", "mary@mail.pl");
            Student student2 = new Student("Klinmer", "Sinstos", "klinger@mail.pl");

            course.addStudent(student);
            course.addStudent(student2);

            session.save(student);
            session.save(student2);


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
