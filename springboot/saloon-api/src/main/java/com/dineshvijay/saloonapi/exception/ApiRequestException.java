package com.dineshvijay.saloonapi.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    private final int code;
    private final HttpStatus httpStatus;

    public ApiRequestException(String message, int code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    public ApiRequestException(String message, Throwable cause, int code, HttpStatus httpStatus) {
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
