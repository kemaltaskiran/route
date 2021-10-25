package com.trustsoft.route.util;

import com.trustsoft.route.dto.RouteResponse;
import java.util.List;

public class DtoBuilder {

  public static final RouteResponse buildRouteResponse() {
    return RouteResponse.builder()
        .route(List.of(TestConstants.COUNTRY_ITA, TestConstants.COUNTRY_AUT,
            TestConstants.COUNTRY_CZE))
        .build();
  }


}
