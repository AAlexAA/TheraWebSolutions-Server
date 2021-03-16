package com.thera.TheraWebSolutionsServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestHandlerException {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc){
        UserErrorResponse userErrorResponse = new UserErrorResponse(HttpStatus.NOT_FOUND.value(),
                exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc){
        UserErrorResponse userErrorResponse = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
