package com.dineshvijay.saloonapi.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseWrapper {
    private final Object object;
    private final int statusCode;
    private String message;
    private final HttpStatus httpStatus;

    public ResponseWrapper(Object object, int statusCode, String message, HttpStatus httpStatus) {
        this.object = object;
        this.statusCode = statusCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<Object> wrap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status",  status());
        map.put("data", this.object);
        return new ResponseEntity<>(map, httpStatus);
    }

    private Map<String, Object> status() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.message);
        map.put("code", this.statusCode);
        map.put("timestamp", ZonedDateTime.now(ZoneId.of("Z")));
        return map;
    }

}
