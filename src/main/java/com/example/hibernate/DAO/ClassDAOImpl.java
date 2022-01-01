package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Hibernate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier("class")
@Transactional
@NoArgsConstructor
@Slf4j
public class ClassDAOImpl implements ClassDAO{
    Session session = Hibernate.getSessionFactory().openSession();

    @Override
    public void addSubject(String id, ClassHibernateEntity classHibernateEntity) {
        log.info("Adding a subject {} to {}", classHibernateEntity.getSub_name(), classHibernateEntity.getCourse_id());
        Transaction tx = session.beginTransaction();
        try{
            session.save(classHibernateEntity);
            tx.commit();
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public void getSubject(String id) {
        System.out.println("Subject details for " + id);
        Query query=session.createQuery("select new list(subject.sub_id,subject.sub_name) from subject subject where subject.course_id=:course");
        query.setParameter("course",id);
        Object list = query.getResultList();
        System.out.println("Result = " + list.toString());
    }

    @Override
    public void getStudents(String course_id) {
        System.out.println("Fetching all students for " + course_id);
        Query query=session.createQuery("select new list(student.name,student.id,student.email) from student student, student_course stu_course where stu_course.course_id = :course and stu_course.student_id = student.id");
        query.setParameter("course",course_id);
        Object list = query.getResultList();
        System.out.println("Result = " + list.toString());
    }
}