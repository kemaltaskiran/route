package com.trustsoft.route.controller;

import static com.trustsoft.route.util.TestConstants.COUNTRY_AUT;
import static com.trustsoft.route.util.TestConstants.COUNTRY_CZE;
import static com.trustsoft.route.util.TestConstants.COUNTRY_ITA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import com.trustsoft.route.dto.RouteResponse;
import com.trustsoft.route.service.RouteService;
import com.trustsoft.route.util.DtoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

  @Mock
  private RouteService routeService;

  @InjectMocks
  private RouteController controller;

  @Test
  void givenOriginAndDestinationWhenGetRouteThenResultIsOk() {
    // Arrange
    final RouteResponse companyResponse = DtoBuilder.buildRouteResponse();

    when(routeService.getRoute(COUNTRY_ITA, COUNTRY_CZE)).thenReturn(companyResponse);

    // Act
    final ResponseEntity<RouteResponse> result = controller
        .getRoute(COUNTRY_ITA, COUNTRY_CZE);
    // Assert
    assertAll(
        () -> assertThat(result).isNotNull(),
        () -> assertThat(result.getStatusCodeValue()).isEqualTo(OK.value()),
        () -> assertThat(result.getBody().getRoute()).contains(COUNTRY_AUT)
    );
    verify(routeService).getRoute(COUNTRY_ITA, COUNTRY_CZE);
  }

}
