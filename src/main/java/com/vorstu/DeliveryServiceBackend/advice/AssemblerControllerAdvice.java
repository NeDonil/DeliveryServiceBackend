package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.AssemblerController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = AssemblerController.class)
@Slf4j
public class AssemblerControllerAdvice {

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity handleNoSuchException(NoSuchElementException ex){
        log.warn(ex.toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_IMPLEMENTED);
    }
}
