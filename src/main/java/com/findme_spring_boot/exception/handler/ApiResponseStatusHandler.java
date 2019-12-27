package com.findme_spring_boot.exception.handler;

import com.findme_spring_boot.exception.api.ApiBadRequestException;
import com.findme_spring_boot.exception.api.ApiForbiddenException;
import com.findme_spring_boot.exception.api.ApiInternalServerException;
import com.findme_spring_boot.exception.api.ApiNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiResponseStatusHandler {
    private static final Logger errorLogger = LogManager.getLogger(ResponseStatusHandler.class);

    @ExceptionHandler(value = ApiBadRequestException.class)
    public ResponseEntity<String> apiBadRequestExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiForbiddenException.class)
    public ResponseEntity<String> apiForbiddenExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = ApiInternalServerException.class)
    public ResponseEntity<String> apiInternalServerExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ApiNotFoundException.class)
    public ResponseEntity<String> apiNotFoundExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
