package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.AssemblerController;
import com.vorstu.DeliveryServiceBackend.exception.IllegalOrderOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = AssemblerController.class)
@Slf4j
public class AssemblerControllerAdvice {

    @ExceptionHandler(IllegalOrderOperationException.class)
    public ResponseEntity illegalOrderOperationException(IllegalOrderOperationException ex){
        log.warn(ex.toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
