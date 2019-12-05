package com.findme_spring_boot.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not found")
public class ApiNotFoundException extends ApiException {
    public ApiNotFoundException() {
    }

    public ApiNotFoundException(String message) {
        super(message);
    }

    public ApiNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiNotFoundException(Throwable cause) {
        super(cause);
    }
}
