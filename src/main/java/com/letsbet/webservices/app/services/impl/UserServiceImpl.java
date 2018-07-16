package com.letsbet.webservices.app.services.impl;

import com.letsbet.webservices.app.dao.UserDAO;
import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.model.resources.UserResource;
import com.letsbet.webservices.app.services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserResource getUser(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        return toResource(userDAO.getUserByUID(uid));
    }

    @Override
    @Transactional
    public UserResource createUser(UserResource userResource, String uid) {
        if (StringUtils.isEmpty(uid) || userResource == null) {
            return null;
        }
        User user = toEntity(userResource);
        user.setUid(uid);
        userDAO.createUser(user);
        return toResource(user);
    }

    @Override
    @Transactional
    public UserResource changeUser(String uid, JSONObject params) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        User user = userDAO.getUserByUID(uid);

        if (params == null) {
            return toResource(user);
        }

        if (params.has("username")) {
            user.setUsername(params.getString("username"));
        }
        if (params.has("avatarUrl")) {
            user.setAvatarUrl(params.getString("avatarUrl"));
        }
        if (params.has("points")) {
            user.setPoints(params.getInt("points"));
        }
        if (params.has("uid")) {
            user.setUid(params.getString("uid"));
        }
        if (params.has("isPremiumUser")) {
            user.setPremium(params.getBoolean("isPremiumUser"));
        }

        userDAO.updateUser(user);

        return toResource(user);
    }

    @Override
    @Transactional
    public UserResource getUserById(Long id) {
        return toResource(userDAO.getUserById(id));
    }

    @Override
    public UserResource toResource(User user) {
        return user != null ? new UserResource(user) : null;
    }

    @Override
    public User toEntity(UserResource resource) {
        return resource != null ? new User(resource) : null;
    }
}
