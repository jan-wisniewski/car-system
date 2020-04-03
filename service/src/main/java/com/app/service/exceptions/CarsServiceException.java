package com.app.service.exceptions;

public class CarsServiceException extends RuntimeException {
    public CarsServiceException (String message){
        super(message);
    }
}
