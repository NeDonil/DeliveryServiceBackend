package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.CourierController;
import com.vorstu.DeliveryServiceBackend.controllers.CustomerController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = CustomerController.class)
@Slf4j
public class CustomerControllerAdvice {
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity handleNoSuchElementException(UnsupportedOperationException ex){
        log.warn(ex.toString());
        return new ResponseEntity( HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException ex){
        log.warn(ex.toString());
        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }
}