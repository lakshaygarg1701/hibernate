package com.example.hibernate.Entitties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Data @NoArgsConstructor
@Entity(name = "roles")
public class RolesHibernateEntity {
    @Id @GeneratedValue(strategy = AUTO, generator = "roles_generator")
    private int s_no;
    private String roleid;
    private String rolename;

    public RolesHibernateEntity(@JsonProperty("id") String roleid,
                                @JsonProperty("name") String rolename){
        this.roleid=roleid;
        this.rolename=rolename;
    }
}
