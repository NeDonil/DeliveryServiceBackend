package com.vorstu.DeliveryServiceBackend.exception;

public class IllegalOrderOperationException extends RuntimeException{
    public IllegalOrderOperationException(String message){
        super(message);
    }

}
