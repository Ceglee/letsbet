package com.letsbet.webservices.app.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.letsbet.webservices.app.model.resources.UserResource;
import com.letsbet.webservices.app.model.response.GeneralResponse;
import com.letsbet.webservices.app.security.FirebaseUserDetails;
import com.letsbet.webservices.app.services.UserService;
import com.letsbet.webservices.app.views.UserViews;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class UserController extends CommonController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @JsonView(UserViews.Details.class)
    UserResource getUser(Principal principal) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        return userService.getUser(details.getId());
    }

    @PostMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse createUser(@RequestBody @JsonView(UserViews.Create.class) UserResource userResource,
                                      Principal principal) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        userService.createUser(userResource, details.getId());
        return build201("User created", details.getId());
    }

    @PatchMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @JsonView(UserViews.Details.class)
    UserResource patchUser(@RequestBody Map<String, Object> json, Principal principal) {
        FirebaseUserDetails details = getFirebaseUser(principal);
        return userService.changeUser(details.getId(), new JSONObject(json));
    }

    @GetMapping(path = "/api/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    @JsonView(UserViews.PublicData.class)
    UserResource getUserPublicData(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }
}
