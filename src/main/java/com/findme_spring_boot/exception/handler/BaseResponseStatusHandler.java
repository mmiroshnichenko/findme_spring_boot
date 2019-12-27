package com.findme_spring_boot.exception.handler;

import com.findme_spring_boot.exception.InternalServerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class BaseResponseStatusHandler {
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest request, Exception e) throws InternalServerException {
        throw new InternalServerException(e.getMessage());
    }
}
