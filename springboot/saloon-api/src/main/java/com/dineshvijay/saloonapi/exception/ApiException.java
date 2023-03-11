package com.dineshvijay.saloonapi.exception;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ApiException {
    private final String message;
    private final int statusCode;
    private final ZonedDateTime timeStamp;

    public ApiException(String message, int statusCode, ZonedDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }
}
