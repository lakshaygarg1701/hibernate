//package com.example.hibernate.Service;
//
//import com.example.hibernate.DAO.ClassDAO;
//import com.example.hibernate.DAO.StudentDAO;
//import com.example.hibernate.Entitties.ClassHibernateEntity;
//import com.example.hibernate.Entitties.StudentHibernateEntity;
//import com.example.hibernate.Entitties.Student_Course;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ClassService {
//    private ClassDAO classDAO;
//
//    public ClassService(@Qualifier("class") StudentDAO studentDAO) {
//        this.classDAO = classDAO;
//    }
//
//    public void insert(ClassHibernateEntity classHibernateEntity){
//        classDAO.addSubject(classHibernateEntity.getSub_id(),classHibernateEntity);
//    }
////    public List<StudentHibernateEntity> get(){return studentDAO.getStudent();}
////    public void update(int id, StudentHibernateEntity studentHibernate) {studentDAO.updateStudent(id, studentHibernate);}
////    public void getStudent(int id){studentDAO.getStudent(id);}
////
////    public void updateStudent(int id, StudentHibernateEntity studentHibernate) {studentDAO.updateStudent(id, studentHibernate);}
//}
