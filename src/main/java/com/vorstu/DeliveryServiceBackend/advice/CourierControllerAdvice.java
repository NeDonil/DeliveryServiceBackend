package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.CourierController;
import com.vorstu.DeliveryServiceBackend.exception.IllegalOrderOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = CourierController.class)
@Slf4j
public class CourierControllerAdvice {
    @ExceptionHandler(IllegalOrderOperationException.class)
    public ResponseEntity illegalOrderOperationException(IllegalOrderOperationException ex){
        log.warn(ex.toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
