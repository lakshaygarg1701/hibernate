package com.example.hibernate.DAO;

import com.example.hibernate.Entitties.RolesHibernateEntity;
import com.example.hibernate.Entitties.StudentHibernateEntity;
import com.example.hibernate.Hibernate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Component @Qualifier(value = "role")
@Transactional
@RequiredArgsConstructor @Slf4j
public class RolesDAOImpl implements RolesDAO{
    Session session = Hibernate.getSessionFactory().openSession();

    @Override
    public void addRole(RolesHibernateEntity rolesHibernateEntity) {
        Transaction tx = session.beginTransaction();
        log.info("Writing role {}", rolesHibernateEntity.getRolename());
        session.saveOrUpdate(rolesHibernateEntity);
        tx.commit();
    }

    @Override
    public RolesHibernateEntity findRoleByName(String name) {
        Query query = session.createQuery("from role_hibernate where rolename=:name");
        query.setParameter("name",name);
        RolesHibernateEntity rolesHibernateEntity = (RolesHibernateEntity) query.getSingleResult();
        return rolesHibernateEntity;
    }
}
