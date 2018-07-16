package com.letsbet.webservices.app.dao;

import com.letsbet.webservices.app.model.entities.User;

public interface UserDAO {

    User getUserByUID(String uid);
    User getUserById(Long id);
    void createUser(User user);
    void updateUser(User user);
}
