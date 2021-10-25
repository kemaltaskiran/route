package com.trustsoft.route.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class BaseController {

  public static final String SEPARATOR = "/";
  public static final String BASE_PATH = SEPARATOR;

  protected static final String ROUTES = "routes";

  protected static final String ROUTING_PATH = SEPARATOR + "routing";
  protected static final String ROUTING_BETWEEN_PATH =
      ROUTING_PATH + "/{origin}" + "/{destination}";

  protected static final String SUCCESSFUL_OPERATION = "Successful operation.";
  protected static final String INVALID_PARAMETERS = "Invalid parameters";

  protected static final String JSON = APPLICATION_JSON_VALUE;
}
