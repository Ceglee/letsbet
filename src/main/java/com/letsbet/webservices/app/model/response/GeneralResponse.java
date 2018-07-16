package com.letsbet.webservices.app.model.response;

public class GeneralResponse {

    private String message;
    private int httpCode;
    private String errorCode;

    public GeneralResponse() {
    }

    public GeneralResponse(String message, int httpCode, String errorCode) {
        this.message = message;
        this.httpCode = httpCode;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
