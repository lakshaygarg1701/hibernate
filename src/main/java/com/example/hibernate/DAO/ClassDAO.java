package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.ClassHibernateEntity;

public interface ClassDAO {
    public void addSubject(String id, ClassHibernateEntity classHibernateEntity);
    public void getSubject(String id);
    public void getStudents(String course_id);
}
