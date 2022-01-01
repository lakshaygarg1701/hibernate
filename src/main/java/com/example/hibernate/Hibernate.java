package com.example.hibernate;

import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Entitties.Student_Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
//@EnableTransactionManagement
//@ComponentScan(value = "com.example.hibernate")
public class Hibernate {
    private static SessionFactory sessionFactory;
//    @Autowired
    private static StandardEnvironment standardEnvironment;

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/testhibernate");
            props.put("hibernate.connection.username", "hibernate");
            props.put("hibernate.connection.password", "hib3rnat3");
            props.put("hibernate.hbm2ddl.auto","create");
            props.put("hibernate.current_session_context_class","thread");
//            props.put("hibernate.show_sql","true");
//            props.put("hibernate.format_sql","true");
//            props.put("hibernate.connection.autocommit","true");
            configuration.setProperties(props);

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
            configuration.addAnnotatedClass(StudentHibernateEntity.class);
//            configuration.addAnnotatedClass(ClassHibernateEntity.class);
//            configuration.addAnnotatedClass(Student_Course.class);
            configuration.addAnnotatedClass(RolesHibernateEntity.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            System.out.println(standardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) sessionFactory = buildSessionJavaConfigFactory();
        return sessionFactory;
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public static HibernateTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        return hibernateTransactionManager;
    }
}