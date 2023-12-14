package com.vorstu.DeliveryServiceBackend.advice;


import com.vorstu.DeliveryServiceBackend.controllers.ProductController;
import com.vorstu.DeliveryServiceBackend.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = ProductController.class)
@Slf4j
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity productNotFoundException(ProductNotFoundException ex){
        log.warn(ex.toString());
        return new ResponseEntity( HttpStatus.NOT_FOUND);
    }
}
