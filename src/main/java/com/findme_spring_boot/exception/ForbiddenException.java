package com.findme_spring_boot.exception;

public class ForbiddenException extends Exception {
    public ForbiddenException(String message) {
        super(message);
    }
}
