package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Entitties.Student_Course;
import com.example.hibernate.Hibernate;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Component
@Qualifier("student")
@Transactional
@NoArgsConstructor
public class StudentDAOImpl implements StudentDAO {
    Session session = Hibernate.getSessionFactory().openSession();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String addStudent(@NotNull StudentHibernateEntity studentHibernateEntity, Student_Course student_course) {
        System.out.println("Welcome your new classmate, " + studentHibernateEntity.getName() + " 🙂");
//        System.out.println(studentHibernateEntity.getName() + '\t'
//                + studentHibernateEntity.getEmail() + '\t' + studentHibernateEntity.getId());
        Transaction tx = session.beginTransaction();
        try{
            LOGGER.info("Adding a new classmate");
            session.saveOrUpdate(studentHibernateEntity);
            LOGGER.info("Adding to course table");
            session.saveOrUpdate(student_course);
            tx.commit();
//            if(tx!=null){
//                tx.rollback();
//            }
//            System.out.println(tx.getStatus());
        }catch(Exception exception){
            System.out.println(exception);
        }
        return null;
    }

//    @Override
//    public void addRoleToStudent(String username, String rolename) {
//        StudentHibernateEntity studentHibernateEntity = new StudentDAOImpl().findStudentByName(username);
//        RolesHibernateEntity rolesHibernateEntity = new RolesDAOImpl().findRoleByName(rolename);
//        studentHibernateEntity.getRole().add(rolesHibernateEntity);
//    }

    @Override
    public void updateStudent(String id, StudentHibernateEntity studentHibernate) {
        System.out.println("Updating credentials for " + id);
        Transaction tx = session.beginTransaction();
        try{
            Query query;
            query = session.createQuery("update student_hibernate set name=:name, age=:age, email=:email, course=:course where adm_no=:id");
            query.setParameter("id",id);
            query.setParameter("name",studentHibernate.getName());
            query.setParameter("age",studentHibernate.getAge());
            query.setParameter("email",studentHibernate.getEmail());
            query.setParameter("course",studentHibernate.getCourse());
//            System.out.println(query.getParameters());
            query.executeUpdate();
            query = session.createQuery("update student_course set course_id=:course, semester=:semester where student_id=:id");
            query.setParameter("course",studentHibernate.getCourse());
            query.setParameter("semester",studentHibernate.getSemester());
            query.setParameter("id",id);
            query.executeUpdate();
            tx.commit();
//            if(tx!=null){
//                tx.rollback();
//            }
//            System.out.println(tx.getStatus());
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public List<StudentHibernateEntity> getStudent() {
        System.out.println("Getting all students in a queue");
        Query query = session.createQuery("from student_hibernate");
        List<StudentHibernateEntity> list = query.getResultList();
        for(int i=0;i< list.size();i++){
            System.out.println(list.get(i).toString());
//            System.out.println(list.get(i).getClass().getName());
        }
        return list;
    }

    @Override
    public List getStudent(String id) {
//        System.out.println("Getting info of your classmate");
        Query query = session.createQuery("select new list(student.id,student.name,stu_course.course_id,stu_course.semester,stu_class.sub_id,stu_class.sub_name) from student_hibernate student,student_course stu_course,class_hibernate stu_class where student.id = stu_course.student_id and student.id = :a and stu_course.course_id = stu_class.course_id and stu_course.semester = stu_class.semester");
        query.setParameter("a",id);
//        System.out.println(query);
        List list = query.getResultList();
        if(list.size()!=0){
            LOGGER.info("Get student details successful");
        }
        else
        {
            LOGGER.error("No student with this Admission No" + " " + new NullPointerException());
        }
//        System.out.println(list.getClass());
        System.out.println("Result = " + list.toString());
        return list;
    }

    public StudentHibernateEntity findStudentByName(String name){
        Query query = session.createQuery("from student_hibernate where username=:name");
        query.setParameter("name",name);
        StudentHibernateEntity studentHibernateEntity = (StudentHibernateEntity) query.getSingleResult();
        return studentHibernateEntity;
    }
}