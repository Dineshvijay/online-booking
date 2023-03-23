package com.dineshvijay.saloonapi.exception;

import org.springframework.http.HttpStatus;

public class SalonException extends RuntimeException {

    private final int code;
    private final HttpStatus httpStatus;

    public SalonException(String message, int code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    public SalonException(String message, Throwable cause, int code, HttpStatus httpStatus) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
