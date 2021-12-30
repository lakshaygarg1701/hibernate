package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Entitties.Student_Course;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StudentDAO {
//    public String addStudent(StudentHibernateEntity studentHibernate);

    String addStudent(@NotNull StudentHibernateEntity studentHibernateEntity, Student_Course student_course);
//    void addRoleToStudent(String username, String rolename);
    public void updateStudent(String id, StudentHibernateEntity studentHibernate);
    public List<StudentHibernateEntity> getStudent();
    public List getStudent(String id);
    public StudentHibernateEntity findStudentByName(String name);
    }
