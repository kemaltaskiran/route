package com.trustsoft.route.exception;

import java.io.Serializable;

public class RouteServiceException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -3990456126556903069L;

  public RouteServiceException(String message) {
    super(message);
  }

}
