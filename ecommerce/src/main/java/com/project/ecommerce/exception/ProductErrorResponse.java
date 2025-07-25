package com.project.ecommerce.exception;

import java.time.LocalDateTime;

public class ProductErrorResponse {

    private LocalDateTime timeStamp;

    private int status;

    private String message;

    public ProductErrorResponse(){

    }

    public ProductErrorResponse(LocalDateTime timeStamp, int status, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
