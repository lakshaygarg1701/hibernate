package com.example.hibernate.Entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import com.example.hibernate.Entitties.RolesHibernateEntity;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Table
@Entity(name = "student_hibernate")
@Data
@NoArgsConstructor
public class StudentHibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "student_generator")
    private int s_no;
    @Column(name = "adm_no")
    private String id;
    private String name;
    private int age;
    private String email;
    private String username;
    private String password;
    @Transient
    private String course;
    @Transient
    private int semester;
//    private Collection<RolesHibernateEntity> role = new ArrayList<>();

    public StudentHibernateEntity(@JsonProperty("adm_no") String id,
                                  @NotNull @JsonProperty("name") String name,
                                  @NotNull @JsonProperty("age") int age,
                                  @NotNull @JsonProperty("email") String email,
                                  @NotNull @JsonProperty("password") String password,
                                  @NotNull @JsonProperty("course") String course,
                                  @NotNull @JsonProperty("semester") int semester) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.semester = semester;
        this.password = password;
        this.username = email.substring(0,7);
    }

    @Override
    public String toString() {
        return "StudentHibernateEntity{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", email=" + email +
//                ", course=" + course +
                '}';
    }
}
