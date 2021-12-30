package com.example.hibernate;

import com.example.hibernate.Entitties.ClassHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
//import com.example.hibernate.Service.ClassService;
import com.example.hibernate.Service.Service;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/")
@RestController
public class StudentHibernateController {
    private Service studentService;
//    private ClassService classService;

    @Autowired
    public StudentHibernateController(Service studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "Student/add")
    public void addstudent(@RequestBody @NotNull StudentHibernateEntity studentHibernateEntity){
        studentService.insertStudent(studentHibernateEntity);
    }

    @GetMapping(path = "Student")
    public List getstudent(){
        return studentService.get();
    }

    @PutMapping(path = "Student/update/{id}")
    public void updatestudent(@PathVariable(value = "id") String id,@RequestBody StudentHibernateEntity studentHibernate){
        studentService.updateStudent(id,studentHibernate);
    }

    @GetMapping(path = "Student/{id}")
    public List getstudent(@PathVariable(value = "id") String id){
        return studentService.getStudent(id);
    }

    @PostMapping(path = "Class/add")
    public void addsubject(@RequestBody @org.jetbrains.annotations.NotNull ClassHibernateEntity classHibernateEntity){
        studentService.insertSubject(classHibernateEntity);
    }

    @GetMapping(path = "Class/{id}")
    public void getsubject(@PathVariable(value = "id") String id){
        studentService.getSubject(id);
        studentService.getSubjectStudents(id);
    }
}
