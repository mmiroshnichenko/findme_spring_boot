package com.findme_spring_boot.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")
public class ApiBadRequestException extends ApiException {
    public ApiBadRequestException() {
        super();
    }

    public ApiBadRequestException(String message) {
        super(message);
    }

    public ApiBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiBadRequestException(Throwable cause) {
        super(cause);
    }
}
