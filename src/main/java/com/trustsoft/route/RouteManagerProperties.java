package com.trustsoft.route;

import com.trustsoft.route.settings.CountrySettings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "route-manager")
public class RouteManagerProperties {

  private CountrySettings country = new CountrySettings();
}
