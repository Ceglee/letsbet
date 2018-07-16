package com.letsbet.webservices.app.services;

public interface Service<T, U> {

    T toResource(U entity);
    U toEntity(T resource);
}
