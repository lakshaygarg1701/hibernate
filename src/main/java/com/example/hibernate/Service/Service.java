package com.example.hibernate.Service;

import com.example.hibernate.DAO.*;
import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Entitties.Student_Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional @org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class Service implements UserDetailsService {
    private final StudentDAO studentDAO;
    private final ClassDAO classDAO;
    private final RolesDAO rolesDAO;
    private final PasswordEncoder passwordEncoder;

//    public Service(@Qualifier("student") StudentDAO studentDAO, @Qualifier("class") ClassDAO classDAO, PasswordEncoder passwordEncoder) {
//        this.studentDAO = studentDAO;
//        this.classDAO = classDAO;
//        this.passwordEncoder = passwordEncoder;
//    }

    public void insertRole(RolesHibernateEntity rolesHibernateEntity){
//        studentHibernateEntity.setPassword(passwordEncoder.encode(studentHibernateEntity.getPassword()));
        rolesDAO.addRole(rolesHibernateEntity);
    }

//    public void addRoleToUser(String username,String rolename){
//        studentDAO.addRoleToStudent(username,rolename);
//    }

    public void insertStudent(StudentHibernateEntity studentHibernateEntity){
        studentHibernateEntity.setPassword(passwordEncoder.encode(studentHibernateEntity.getPassword()));
        studentDAO.addStudent(studentHibernateEntity,
        new Student_Course(studentHibernateEntity.getId(),studentHibernateEntity.getCourse(),studentHibernateEntity.getSemester()));
    }
    public void insertSubject(ClassHibernateEntity classHibernateEntity){
        classDAO.addSubject(classHibernateEntity.getSub_id(),classHibernateEntity);
    }
    public List getStudent(String id){
        List student = studentDAO.getStudent(id);
        return student;
    }
    public void getSubject(String id){classDAO.getSubject(id);}
    public void getSubjectStudents(String id){classDAO.getStudents(id);}
    public List<StudentHibernateEntity> get(){return studentDAO.getStudent();}
    public void update(String id, StudentHibernateEntity studentHibernate) {studentDAO.updateStudent(id, studentHibernate);}
    public void updateStudent(String id, StudentHibernateEntity studentHibernate) {studentDAO.updateStudent(id, studentHibernate);}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentHibernateEntity studentHibernateEntity = studentDAO.findStudentByName(username);
        if(studentHibernateEntity==null){
            log.error("Username not found {}",new UsernameNotFoundException("Username not found"));
            throw new UsernameNotFoundException("Username not found");
        } else {
            log.info("Username found {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        studentHibernateEntity.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
        return new org.springframework.security.core.userdetails.User(studentHibernateEntity.getUsername(),studentHibernateEntity.getPassword(),authorities);
    }
}
