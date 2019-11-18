package com.findme_spring_boot.exception;

public class InternalServerException extends Exception {
    public InternalServerException(String message) {
        super(message);
    }
}
