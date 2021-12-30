package com.example.hibernate.Entitties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity(name = "roles_hibernate")
public class RolesHibernateEntity {
    @Id @GeneratedValue(strategy = AUTO, generator = "roles_generator")
    private Long s_no;
    private String rolename;
}
