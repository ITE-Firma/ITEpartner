package com.example.demo.kunde.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundError(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", "Die angeforderte Seite wurde nicht gefunden");
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}