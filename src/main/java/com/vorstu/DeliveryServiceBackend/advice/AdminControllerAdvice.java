package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.AdminController;
import com.vorstu.DeliveryServiceBackend.exception.IllegalOrderOperationException;
import com.vorstu.DeliveryServiceBackend.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = AdminController.class)
@Slf4j
public class AdminControllerAdvice {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity orderNotFoundException(OrderNotFoundException ex){
        log.warn(ex.toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalOrderOperationException.class)
    public ResponseEntity illegalOrderOperationException(IllegalOrderOperationException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
