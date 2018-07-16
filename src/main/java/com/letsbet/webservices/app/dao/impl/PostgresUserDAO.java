package com.letsbet.webservices.app.dao.impl;

import com.letsbet.webservices.app.dao.UserDAO;
import com.letsbet.webservices.app.model.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class PostgresUserDAO implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresUserDAO.class);
    private EntityManager entityManager;

    @Autowired
    public PostgresUserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUserByUID(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        return entityManager
                .createNamedQuery( "get_user_by_uid", User.class )
                .setParameter( "uid", uid )
                .getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void createUser(User user) {
        if (user != null) {
            entityManager.persist(user);
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.persist(user);
    }
}
