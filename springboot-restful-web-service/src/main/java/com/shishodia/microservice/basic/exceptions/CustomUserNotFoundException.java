package com.shishodia.microservice.basic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomUserNotFoundException extends RuntimeException {

    public CustomUserNotFoundException(String message) {
        super(message);
    }
    
}
