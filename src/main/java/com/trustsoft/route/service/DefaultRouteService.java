package com.trustsoft.route.service;

import com.trustsoft.route.client.CountryClient;
import com.trustsoft.route.dto.Country;
import com.trustsoft.route.dto.RouteResponse;
import com.trustsoft.route.exception.RouteServiceException;
import com.trustsoft.route.util.RouteUtil;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class DefaultRouteService implements RouteService {

  private static final String ORIGIN_COUNTRY_NOT_FOUND = "Origin country not found";
  private static final String DESTINATION_COUNTRY_NOT_FOUND = "Destination country not found";
  private static final String NO_PATH_BETWEEN_ORIGIN_AND_DESTINATION_COUNTRIES =
      "There is no path between origin and destination countries";
  private static final String ORIGIN_COUNTRY_HAS_NO_BORDERS = "Origin country has no borders";
  private static final String DESTINATION_COUNTRY_HAS_NO_BORDERS = "Destination country has no borders";

  private final CountryClient countryClient;

  @Override
  public RouteResponse getRoute(String origin, String destination) {

    var countries = countryClient.getCountries().stream()
        .collect(Collectors.toMap(Country::getName, Function.identity()));

    var originCountry = Optional.ofNullable(countries.get(origin))
        .orElseThrow(() -> {
          return new RouteServiceException(ORIGIN_COUNTRY_NOT_FOUND);
        });

    var destinationCountry = Optional.ofNullable(countries.get(destination))
        .orElseThrow(() -> {
          return new RouteServiceException(DESTINATION_COUNTRY_NOT_FOUND);
        });

    if (!RouteUtil.hasRoute(originCountry.getContinent(), destinationCountry.getContinent())) {
      throw new RouteServiceException(NO_PATH_BETWEEN_ORIGIN_AND_DESTINATION_COUNTRIES);
    }

    if (!origin.equals(destination)) {
      if (CollectionUtils.isEmpty(originCountry.getBorders())) {
        throw new RouteServiceException(ORIGIN_COUNTRY_HAS_NO_BORDERS);
      }
      if (CollectionUtils.isEmpty(destinationCountry.getBorders())) {
        throw new RouteServiceException(DESTINATION_COUNTRY_HAS_NO_BORDERS);
      }
    }

    return RouteResponse.builder()
        .route(RouteUtil.find(originCountry, destinationCountry, countries).stream()
            .map(Country::getName).collect(Collectors.toList()))
        .build();
  }
}
