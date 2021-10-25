package com.trustsoft.route.service;

import com.trustsoft.route.dto.RouteResponse;

public interface RouteService {

  RouteResponse getRoute(String origin, String destination);
}
