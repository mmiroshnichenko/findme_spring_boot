package com.findme_spring_boot.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
public class ApiInternalServerException extends ApiException {
    public ApiInternalServerException() {
    }

    public ApiInternalServerException(String message) {
        super(message);
    }

    public ApiInternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiInternalServerException(Throwable cause) {
        super(cause);
    }
}
