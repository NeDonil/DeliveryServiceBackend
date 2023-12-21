package com.vorstu.DeliveryServiceBackend.advice;

import com.vorstu.DeliveryServiceBackend.controllers.AuthController;
import com.vorstu.DeliveryServiceBackend.exception.DuplicateEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = AuthController.class)
@Slf4j
public class AuthControllerAdvice {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity handleDuplicateEmailExceptionHandler(DuplicateEmailException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        log.warn(ex.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
