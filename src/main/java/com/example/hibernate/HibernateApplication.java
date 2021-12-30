package com.example.hibernate;

import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Service.Service;
import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication @Slf4j
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);

	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		System.out.println("Initializing password encoder");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.getClass());
		return bCryptPasswordEncoder;
	}

	@Bean
	CommandLineRunner runner(Service service){
		return args -> {
			service.insertRole(new RolesHibernateEntity(null,"Admin"));
			service.insertRole(new RolesHibernateEntity(null,"User"));
			service.insertRole(new RolesHibernateEntity(null,"Super_User"));
			service.insertRole(new RolesHibernateEntity(null,"Manager"));

//			service.addRoleToUser("lg17881","Admin");
//			service.addRoleToUser("pc89589","Super_User");
//			service.addRoleToUser("ak98558","Manager");
//			service.addRoleToUser("ll98264","User");

			service.insertStudent(new StudentHibernateEntity("2100832600","Lakshay",23,"lg17881@citi.com","1234","MBA",1));
			service.insertStudent(new StudentHibernateEntity("2100832601","Priya",23,"pc89589@citi.com","1234","MBA",2));
			service.insertStudent(new StudentHibernateEntity("2100832602","Aman",23,"ak98558@citi.com","1234","MCA_NEW",1));
			service.insertStudent(new StudentHibernateEntity("2100832603","Avneet",23,"ak22230@citi.com","1234","MCA_NEW",2));
			service.insertStudent(new StudentHibernateEntity("2100832604","Naina",23,"nn98432@citi.com","1234","MCA_NEW",2));
			service.insertStudent(new StudentHibernateEntity("2100832605","Gunjan",23,"gm22214@citi.com","1234","MCA_NEW",3));

			service.insertSubject(new ClassHibernateEntity("MCS101","Basics","MCA_NEW",1));
			service.insertSubject(new ClassHibernateEntity("MCS201","Networking","MCA_NEW",2));
			service.insertSubject(new ClassHibernateEntity("MCS202","AI","MCA_NEW",2));
			service.insertSubject(new ClassHibernateEntity("MCS301","Computer Graphics","MCA_NEW",3));
			service.insertSubject(new ClassHibernateEntity("MBA101","Basics","MBA",1));
			service.insertSubject(new ClassHibernateEntity("MBA201","Marketing","MBA",2));
			service.insertSubject(new ClassHibernateEntity("MBA202","Management","MBA",2));
			service.insertSubject(new ClassHibernateEntity("MBA203","VR","MBA",2));
			service.insertSubject(new ClassHibernateEntity("MBA204","GD","MBA",2));
		};
	}

}
