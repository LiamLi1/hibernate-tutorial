package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class ReadStudentDemo {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				 .configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Student.class)
				 .buildSessionFactory();
		
		// start a transaction
		Session session = factory.getCurrentSession();
		session.beginTransaction();		
		
		//retrieve student based on the id: primary key
		Student tempStudent = session.get(Student.class, 3);
		System.out.println(tempStudent);
		
		List<Student> list = session.createQuery("from Student s "+
												 "where s.email LIKE '%gmail.com' ").getResultList();
		
		displayStudents(list);
		
		tempStudent.setLastName("new@gmail.com");
		
		displayStudents(list);
		session.close();
		factory.close();
		
	}

	private static void displayStudents(List<Student> list) {
		for (Student student : list) {
			System.out.println(student);
		}
	}
}
