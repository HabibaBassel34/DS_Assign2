package com.example.customerwallet.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return error;
    }
}