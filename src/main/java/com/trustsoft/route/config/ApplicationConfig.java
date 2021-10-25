package com.trustsoft.route.config;

import com.trustsoft.route.RouteManagerProperties;
import com.trustsoft.route.client.CountryClient;
import com.trustsoft.route.settings.CountrySettings;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(RouteManagerProperties.class)
public class ApplicationConfig {

  private final RouteManagerProperties routeManagerProperties;

  public ApplicationConfig(RouteManagerProperties routeManagerProperties) {
    this.routeManagerProperties = routeManagerProperties;
  }

  @Bean
  public CountrySettings getCountrySettings() {
    return routeManagerProperties.getCountry();
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    List<HttpMessageConverter<?>> converters = new ArrayList<>();
    final MappingJackson2HttpMessageConverter messageConverter =
        new MappingJackson2HttpMessageConverter();
    messageConverter.setSupportedMediaTypes(List.of(MediaType.ALL));
    converters.add(messageConverter);
    restTemplate.setMessageConverters(converters);
    return restTemplate;
  }

  @Bean
  public CountryClient countryClient(RestTemplate restTemplate,
      CountrySettings countrySettings) {
    return new CountryClient(restTemplate, countrySettings);
  }

}
