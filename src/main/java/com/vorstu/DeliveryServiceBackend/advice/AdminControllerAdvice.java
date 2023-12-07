package com.vorstu.DeliveryServiceBackend.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AdminControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchException(NoSuchElementException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
