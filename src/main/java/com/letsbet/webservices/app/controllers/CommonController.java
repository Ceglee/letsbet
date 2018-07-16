package com.letsbet.webservices.app.controllers;

import com.letsbet.webservices.app.model.response.CreatedResponse;
import com.letsbet.webservices.app.model.response.GeneralResponse;
import com.letsbet.webservices.app.security.FirebaseUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public class CommonController {

    protected FirebaseUserDetails getFirebaseUser(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("principal parameter cannot be null");
        }
        return (FirebaseUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    protected GeneralResponse build200 (String message) {
        return buildGeneralResponse(message, HttpStatus.OK.value(), null);
    }

    protected GeneralResponse build201 (String message, Object id) {
        return buildCreatedResponse(message, id);
    }

    protected GeneralResponse build400 (String message, String errorCode) {
        return buildGeneralResponse(message, HttpStatus.BAD_REQUEST.value(), errorCode);
    }

    private GeneralResponse buildGeneralResponse(String message, int httpCode, String errorCode) {
        return new GeneralResponse(message, httpCode, errorCode);
    }

    private CreatedResponse buildCreatedResponse(String message, Object id) {
        return new CreatedResponse(message, HttpStatus.CREATED.value(), null, id);
    }
}
