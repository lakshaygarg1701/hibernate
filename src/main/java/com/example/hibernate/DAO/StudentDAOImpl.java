package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Entitties.Student_Course;
import com.example.hibernate.Hibernate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@NoArgsConstructor @Slf4j
public class StudentDAOImpl implements StudentDAO {
    Session session = Hibernate.getSessionFactory().openSession();

    @Override
    public String addStudent(@NotNull StudentHibernateEntity studentHibernateEntity, Student_Course student_course) {
        Transaction tx = session.beginTransaction();
        try{
            log.info("Adding a new classmate {} 🙂",studentHibernateEntity.getName());
//            Query query = session.createQuery("insert into student(adm_no, name, email, age, password, username) values (:adm,:name,:email,:age,:pass,:username)");
//            Query query = session.createQuery("insert into student :adm,:name,:email,:age,:pass,:username");
//            query.setParameter("adm",studentHibernateEntity.getId());
//            query.setParameter("name",studentHibernateEntity.getName());
//            query.setParameter("email",studentHibernateEntity.getEmail());
//            query.setParameter("age",studentHibernateEntity.getAge());
//            query.setParameter("pass",studentHibernateEntity.getPassword());
//            query.setParameter("username",studentHibernateEntity.getUsername());
            session.save(studentHibernateEntity);
            log.info("Adding to course table");
            session.saveOrUpdate(student_course);
            tx.commit();
        }catch(Exception exception){
            System.out.println(exception);
        }
        return null;
    }


    @Override
    public void updateStudent(String id, StudentHibernateEntity studentHibernate) {
        System.out.println("Updating credentials for " + id);
        Transaction tx = session.beginTransaction();
        try{
            Query query;
            query = session.createQuery("update student set name=:name, age=:age, email=:email, course=:course where adm_no=:id");
            query.setParameter("id",id);
            query.setParameter("name",studentHibernate.getName());
            query.setParameter("age",studentHibernate.getAge());
            query.setParameter("email",studentHibernate.getEmail());
            query.setParameter("course",studentHibernate.getCourse());
            query.executeUpdate();
            query = session.createQuery("update student_course set course_id=:course, semester=:semester where student_id=:id");
            query.setParameter("course",studentHibernate.getCourse());
            query.setParameter("semester",studentHibernate.getSemester());
            query.setParameter("id",id);
            query.executeUpdate();
            tx.commit();
        }
        catch(Exception exception){
            System.out.println(exception);
        }
    }

    @Override
    public List<StudentHibernateEntity> getStudent() {
        System.out.println("Getting all students in a queue");
        Query query = session.createQuery("from student");
        List<StudentHibernateEntity> list = query.getResultList();
        for(int i=0;i< list.size();i++){
            System.out.println(list.get(i).toString());
        }
        return list;
    }

    @Override
    public List getStudentDetails(String id) {
        Query query = session.createQuery("select new list(student.id,student.name,stu_course.course_id,stu_course.semester,stu_class.sub_id,stu_class.sub_name) " +
                "from student student,student_course stu_course,subject stu_class " +
                "where student.username = :a " +
                    "and student.id = stu_course.student_id " +
                    "and stu_course.course_id = stu_class.course_id " +
                    "and stu_course.semester = stu_class.semester");
        query.setParameter("a",id);
        List list = query.getResultList();
        if(list.size()!=0){
            log.info("Get student details successful");
        }
        else
        {
            log.error("No student with this Admission no {}", new NullPointerException());
        }
        System.out.println("Result = " + list.toString());
        return list;
    }

    @Override
    public StudentHibernateEntity getStudent(String id) {
        Query query = session.createQuery("from student where username = :a");
        query.setParameter("a",id);
        StudentHibernateEntity list = (StudentHibernateEntity) query.getSingleResult();
        if(list!=null){
            log.info("Get student details successful");
        }
        else
        {
            log.error("No student with this Admission no {}", new NullPointerException());
        }
        System.out.println("Result = " + list.toString());
        return list;
    }

    public StudentHibernateEntity findStudentByName(String name){
        Query query = session.createQuery("from student where username=:name");
        query.setParameter("name",name);
        StudentHibernateEntity studentHibernateEntity = (StudentHibernateEntity) query.getSingleResult();
        return studentHibernateEntity;
    }
}