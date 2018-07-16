package com.letsbet.webservices.app.services;

import com.letsbet.webservices.app.model.entities.User;
import com.letsbet.webservices.app.model.resources.UserResource;
import org.json.JSONObject;

public interface UserService extends Service<UserResource, User> {

    UserResource getUser(String uid);
    UserResource createUser(UserResource userResource, String uid);
    UserResource changeUser(String uid, JSONObject params);
    UserResource getUserById(Long id);
}
