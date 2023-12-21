package com.vorstu.DeliveryServiceBackend.exception;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(String message){
        super(message);
    }
}
