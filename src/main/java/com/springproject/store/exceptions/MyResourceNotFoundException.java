package com.springproject.store.exceptions;

public class MyResourceNotFoundException extends RuntimeException{
    public MyResourceNotFoundException(String message) {
        super(message);
    }
}
