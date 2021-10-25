package com.trustsoft.route.controller;

import static com.trustsoft.route.controller.BaseController.BASE_PATH;
import static com.trustsoft.route.controller.BaseController.ROUTES;

import com.trustsoft.route.dto.RouteResponse;
import com.trustsoft.route.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to routes.
 */
@Tag(name = ROUTES, description = "The Routing API")
@RequestMapping(path = BASE_PATH)
@RestController
@Validated
@RequiredArgsConstructor
public class RouteController extends BaseController {

  private final RouteService routeService;

  @Operation(description = "Get company", tags = {ROUTES})
  @ApiResponse(responseCode = "200", description = SUCCESSFUL_OPERATION)
  @ApiResponse(responseCode = "400", description = INVALID_PARAMETERS,
      content = @Content(schema = @Schema(implementation = String.class)))
  @GetMapping(value = ROUTING_BETWEEN_PATH, produces = JSON)
  public ResponseEntity<RouteResponse> getRoute(
      @Parameter(required = true) @PathVariable String origin,
      @Parameter(required = true) @PathVariable String destination) {
    final RouteResponse response = routeService.getRoute(origin, destination);
    return ResponseEntity.ok(response);
  }

}
