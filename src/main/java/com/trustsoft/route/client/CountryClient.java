package com.trustsoft.route.client;

import com.trustsoft.route.dto.Country;
import com.trustsoft.route.settings.CountrySettings;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class CountryClient {

  private static final String SCHEME = "https";
  private static final String COUNTRY_API = "/mledoze/countries/master/countries.json";

  private final RestTemplate restTemplate;
  private final CountrySettings countrySettings;

  public List<Country> getCountries() {

    ParameterizedTypeReference<List<Country>> ptr = new ParameterizedTypeReference<>() {
    };

    final UriComponents uriComponents = UriComponentsBuilder.newInstance()
        .scheme(SCHEME)
        .host(countrySettings.getUrl())
        .path(COUNTRY_API)
        .build(true);

    return restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, null, ptr).getBody();
  }

}
