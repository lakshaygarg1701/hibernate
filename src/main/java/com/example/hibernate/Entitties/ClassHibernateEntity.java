package com.example.hibernate.Entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Table
@Entity(name = "class_hibernate")
@Data
@NoArgsConstructor
public class ClassHibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "subject_generator")
    private int s_no;
    @Column(name = "subject_id")
    private String sub_id;
    private String sub_name;
    private String course_id;
    private int semester;

    public ClassHibernateEntity(@NotNull @JsonProperty("Subject_ID") String sub_id,
                                @NotNull @JsonProperty("Subject_Name") String sub_name,
                                @NotNull @JsonProperty("Course") String course,
                                @NotNull @JsonProperty("Semester") int semester) {
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.course_id= course;
        this.semester=semester;
    }
}
