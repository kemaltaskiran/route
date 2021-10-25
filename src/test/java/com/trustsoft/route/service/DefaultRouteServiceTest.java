package com.trustsoft.route.service;

import static com.trustsoft.route.util.RouteUtil.EUROPE;
import static com.trustsoft.route.util.TestConstants.COUNTRY_AUT;
import static com.trustsoft.route.util.TestConstants.COUNTRY_CZE;
import static com.trustsoft.route.util.TestConstants.COUNTRY_ITA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.trustsoft.route.client.CountryClient;
import com.trustsoft.route.dto.Country;
import com.trustsoft.route.dto.RouteResponse;
import com.trustsoft.route.util.DtoBuilder;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class DefaultRouteServiceTest extends BaseServiceTest {

  @Mock
  private CountryClient countryClient;

  private DefaultRouteService service;

  @BeforeEach
  void setUp() {
    service = new DefaultRouteService(countryClient);
  }

  @Test
  void givenOriginAndDestinationWhenGetRouteThenResultIsOk() {
    // Arrange
    final List<Country> countries = List.of(
        Country.builder().name(COUNTRY_CZE).borders(List.of(COUNTRY_AUT)).continent(EUROPE).build(),
        Country.builder().name(COUNTRY_AUT).borders(List.of(COUNTRY_ITA)).continent(EUROPE).build(),
        Country.builder().name(COUNTRY_ITA).borders(List.of(COUNTRY_AUT)).continent(EUROPE)
            .build());
    final RouteResponse companyResponse = DtoBuilder.buildRouteResponse();

    when(countryClient.getCountries()).thenReturn(countries);
    // Act
    final RouteResponse result = service.getRoute(COUNTRY_CZE, COUNTRY_ITA);
    // Assert
    assertAll(
        () -> assertThat(result.getRoute()).contains(COUNTRY_AUT)
    );

    verify(countryClient).getCountries();
  }
}
