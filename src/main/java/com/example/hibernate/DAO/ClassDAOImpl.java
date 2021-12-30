package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Hibernate;
import lombok.NoArgsConstructor;
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
public class ClassDAOImpl implements ClassDAO{
    Session session = Hibernate.getSessionFactory().openSession();

    @Override
    public void addSubject(String id, ClassHibernateEntity classHibernateEntity) {
        System.out.println("Adding a subject " + classHibernateEntity.getSub_name() + " to " + classHibernateEntity.getCourse_id());
        Transaction tx = session.beginTransaction();
        try{
            session.save(classHibernateEntity);
            tx.commit();
//            if(tx!=null){
//                tx.rollback();
//            }
//            System.out.println(tx.getStatus());
        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public void getSubject(String id) {
        System.out.println("Subject details for " + id);
        Query query=session.createQuery("select new list(subject.sub_id,subject.sub_name) from class_hibernate subject where subject.course_id=:course");
        query.setParameter("course",id);
        Object list = query.getResultList();
        System.out.println("Result = " + list.toString());
    }

    @Override
    public void getStudents(String course_id) {
        System.out.println("Fetching all students for " + course_id);
        Query query=session.createQuery("select new list(student.name,student.id,student.email) from student_hibernate student, student_course stu_course where stu_course.course_id = :course and stu_course.student_id = student.id");
        query.setParameter("course",course_id);
        Object list = query.getResultList();
        System.out.println("Result = " + list.toString());
    }
}