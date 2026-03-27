package com.springproject.store.exceptions;

import java.util.Date;
import java.util.Map;

public class ErrorMessage {

    private int status;
    private Date timestamp;
    private String message;
    private String path;
    private Map<String, String> errors; // 👈 for validation

    public ErrorMessage(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = new Date();
    }

    public ErrorMessage(int status, String message, String path, Map<String, String> errors) {
        this(status, message, path);
        this.errors = errors;
    }

    public int getStatus() { return status; }
    public Date getTimestamp() { return timestamp; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public Map<String, String> getErrors() { return errors; }
}