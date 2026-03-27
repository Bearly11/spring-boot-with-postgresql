package com.springproject.store.exceptions;

public class DuplicateProductException extends RuntimeException{
    public DuplicateProductException(String message) {
        super(message);
    }
}
