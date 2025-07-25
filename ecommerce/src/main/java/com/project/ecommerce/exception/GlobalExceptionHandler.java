package com.project.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException1 (ProductNotFoundException ex){

        ProductErrorResponse error = new ProductErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException2 (DuplicateUsernameException ex){

        UserErrorResponse error = new UserErrorResponse();
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException3(Exception ex){
        ProductErrorResponse error = new ProductErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("BAD_REQUEST");
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

}
