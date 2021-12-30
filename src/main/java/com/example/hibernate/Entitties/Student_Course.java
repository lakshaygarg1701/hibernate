package com.example.hibernate.Entitties;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity(name = "student_course")
@Data
public class Student_Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "student_course_generator")
    private int s_no;
    private String student_id;
    private String course_id;
    private int semester;

    public Student_Course(String student_id, String course, int semester) {
        this.student_id = student_id;
        this.course_id = course;
        this.semester = semester;
    }

    public Student_Course() {}

    @Override
    public String toString() {
        return "Student_Course{" +
//                "s_no=" + s_no +
                "student_id='" + student_id + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
