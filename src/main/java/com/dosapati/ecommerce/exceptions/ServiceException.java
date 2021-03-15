package com.dosapati.ecommerce.exceptions;

public class ServiceException extends RuntimeException {

    //We can define specific status codes and use the status code to return in terms of exception
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}