package com.trustsoft.route.controller;

import com.trustsoft.route.exception.RouteServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
public class RestControllerAdvice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RouteServiceException.class)
  public ResponseEntity handleRouteServiceException(
      RouteServiceException exception) {
    log.error(exception.getMessage(), exception);
    return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

}


