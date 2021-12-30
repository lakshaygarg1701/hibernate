package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.RolesHibernateEntity;

public interface RolesDAO {
    void addRole(RolesHibernateEntity rolesHibernateEntity);
    RolesHibernateEntity findRoleByName(String name);
}
