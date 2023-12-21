package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.CustomerController;
import com.vorstu.DeliveryServiceBackend.exception.IllegalOrderOperationException;
import com.vorstu.DeliveryServiceBackend.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = CustomerController.class)
@Slf4j
public class CustomerControllerAdvice {
    @ExceptionHandler(IllegalOrderOperationException.class)
    public ResponseEntity illegalOrderOperationException(IllegalOrderOperationException ex){
        log.warn(ex.toString());
        return new ResponseEntity( HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity orderNotFoundException(OrderNotFoundException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}