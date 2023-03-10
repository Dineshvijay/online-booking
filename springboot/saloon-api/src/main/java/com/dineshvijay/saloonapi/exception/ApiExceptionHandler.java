package com.dineshvijay.saloonapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;


@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ApiRequestException.class})
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        var apiException = new ApiException(e.getMessage(), e.getCode(), ZonedDateTime.now(ZoneId.of("Z")));
        var map = new HashMap<String, Object>();
        map.put("status", apiException);
        map.put("data", null);
        return new ResponseEntity<>(map, e.getHttpStatus());
    }
}
