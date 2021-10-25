package com.trustsoft.route.util;

import com.trustsoft.route.dto.Country;
import com.trustsoft.route.exception.RouteServiceException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

@UtilityClass
public class RouteUtil {

  public static final String AFRICA = "africa";
  public static final String ASIA = "asia";
  public static final String EUROPE = "europe";
  private static final String ROUTE_NOT_FOUND = "Route not found";

  private static final List<String> CONTINENTS = List.of(AFRICA, ASIA, EUROPE);

  public static boolean hasRoute(String continentFrom, String continentTo) {
    if (continentFrom.equalsIgnoreCase(continentTo)) {
      return true;
    } else if (CONTINENTS.contains(continentFrom.toLowerCase())
        && CONTINENTS.contains(continentTo.toLowerCase())) {
      return true;
    } else {
      return false;
    }
  }

  public static List<Country> find(Country origin,
      Country destination,
      Map<String, Country> countries) {

    final LinkedList<Country> route = new LinkedList<>();
    final List<LinkedList<Country>> routes = new ArrayList<>();
    final Set<String> visited = new HashSet<>();

    route.add(origin);
    routes.add(route);
    visited.add(origin.getName());

    return searchRecursive(
        destination,
        countries,
        routes,
        visited);
  }

  private static List<Country> searchRecursive(
      Country destination,
      Map<String, Country> countries,
      List<LinkedList<Country>> routes,
      Set<String> visited) {

    final List<LinkedList<Country>> nextRoutes = new ArrayList<>();

    for (LinkedList<Country> route : routes) {
      final Country nextCountry = route.getLast();

      if (CollectionUtils.isEmpty(nextCountry.getBorders())) {
        continue;
      }

      if (nextCountry.getBorders().contains(destination.getName())) {
        route.add(destination);
        return route;
      }

      nextCountry.getBorders().stream().filter(country -> !visited.contains(country))
          .forEach(country -> {
            {
              visited.add(country);
            }
            final LinkedList<Country> newRoute = new LinkedList<>(route);
            newRoute.add(countries.get(country));
            nextRoutes.add(newRoute);
          });
    }

    if (nextRoutes.isEmpty()) {
      throw new RouteServiceException(ROUTE_NOT_FOUND);
    }

    return searchRecursive(
        destination,
        countries,
        nextRoutes,
        visited);
  }

}
