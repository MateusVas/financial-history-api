package com.mateuscarvalho.financialhistory.exception;

import java.time.LocalDateTime;

public class ExceptionDetails {

    private String message;
    private String status;
    private LocalDateTime timeStamp;
    private String details;

    public ExceptionDetails() {
    }

    public ExceptionDetails(String message, String status, LocalDateTime timeStamp,
            String details) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String stackTrace) {
        this.details = details;
    }
}
