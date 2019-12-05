package com.findme_spring_boot.exception.handler;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.exception.ForbiddenException;
import com.findme_spring_boot.exception.InternalServerException;
import com.findme_spring_boot.exception.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseStatusHandler {
    private static final Logger errorLogger = LogManager.getLogger(ResponseStatusHandler.class);

    @ExceptionHandler(value = BadRequestException.class)
    public ModelAndView badRequestExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/badRequest");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ModelAndView forbiddenExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/forbidden");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = InternalServerException.class)
    public ModelAndView internalServerExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/internalError");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ModelAndView notFoundExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        errorLogger.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("errors/notFound");
        modelAndView.addObject("errorMessage", e.getMessage());
        return modelAndView;
    }
}
