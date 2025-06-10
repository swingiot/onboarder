package com.swingiot.onboarder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class InvalidRequestExceptionHandler {
  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException e) {
    return new ResponseEntity<>(Map.of("message", e != null ? e.getMessage() : "Invalid data"), HttpStatus.BAD_REQUEST);
  }
}
