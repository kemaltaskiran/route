package com.trustsoft.route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

  @JsonProperty(value = "cca3")
  private String name;
  @JsonProperty(value = "region")
  private String continent;
  private List<String> borders;
}
