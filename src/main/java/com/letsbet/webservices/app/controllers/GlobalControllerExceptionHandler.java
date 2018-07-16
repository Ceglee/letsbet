package com.letsbet.webservices.app.controllers;

import com.letsbet.webservices.app.model.response.GeneralResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends CommonController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public GeneralResponse handleInvalidDBState() {
        return new GeneralResponse("Request has caused invalid state of data in DB", HttpStatus.CONFLICT.value(), "INVALID_REQUEST");
    }

}
