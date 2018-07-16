package com.letsbet.webservices.app.model.response;

public class CreatedResponse extends GeneralResponse {

    private Object id;

    public CreatedResponse(String message, int httpCode, String errorCode, Object resourceId) {
        super(message, httpCode, errorCode);
        id = resourceId;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
