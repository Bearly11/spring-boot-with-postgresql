package com.springproject.store.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Not found handle
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex){
        return ResponseEntity.status(404).body(Map.of(
                "error",ex.getMessage()
        ));
    }

    // bad request handle
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequest(BadRequestException ex){
        return ResponseEntity.status(400).body(Map.of(
                "error",ex.getMessage()
        ));
    }
}
