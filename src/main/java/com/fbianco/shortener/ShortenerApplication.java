package com.fbianco.shortener;

import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fbianco.shortener.hibernate.HibernateUtil;

@SpringBootApplication
public class ShortenerApplication {

	public static void main(String[] args) {
		ShortUrl emp = new ShortUrl();
		emp.setUrl("www.youtube.com");
		emp.setInsertTime(new Date());
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.persist(emp);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Employee ID="+emp.getId());
		
		//terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
	}
}
