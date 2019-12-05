package com.findme_spring_boot.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "authentication error")
public class ApiForbiddenException extends ApiException {
    public ApiForbiddenException() {
    }

    public ApiForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiForbiddenException(Throwable cause) {
        super(cause);
    }

    public ApiForbiddenException(String message) {
        super(message);
    }
}
