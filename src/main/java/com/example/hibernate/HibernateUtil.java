//package com.example.hibernate;
//
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.service.ServiceRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan(value = {"com.example.hibernate"})
//@PropertySource(value = {"classpath:application.properties"})
//public class HibernateUtil {
//
//    public HibernateUtil(){
//        System.out.println("Inside HibernateUtil constructor");
//        try{
//            sessionFactory().getConfiguration().buildSessionFactory();
//        }catch(Exception ex){
//            System.out.println("Session Factory cannot be made"+'\t'+ex);
//        }
//
//    }
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        System.out.println("Hibernate configuration loaded");
//        sessionFactory.setPackagesToScan("com.example.Hibernate.StudentHibernateEntity");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        System.out.println("Inside sessionFactory Method");
//        return sessionFactory;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/testhibernate");
//        dataSource.setUsername("hibernate");
//        dataSource.setPassword("hib3rnat3");
//        System.out.println("Inside datasource Method");
//        System.out.println(dataSource.getUrl() + '\t' + dataSource.getUsername());
//        return dataSource;
//    }
//
//    @Bean
//    @Autowired
//    public PlatformTransactionManager hibernateTransactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        System.out.println("Inside hibernateTransactionManager Method");
//        return transactionManager;
//    }
//
//    private final Properties hibernateProperties() {
//        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        System.out.println("Hibernate Java Config serviceRegistry created");
//        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        return sessionFactory;
//        System.out.println("Inside hibernateProps Method");
//        return hibernateProperties;
//    }
//
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(sessionFactory);
//        return hibernateTransactionManager;
//    }
//}