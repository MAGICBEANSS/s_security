package com.example.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SampleResponseDTO {

  @JsonProperty("page")
  private String page;

  @JsonProperty("per_page")
  private String perpage;

  @JsonProperty("total")
  private String total;

  @JsonProperty("total_pages")
  private String total_pages;

  List<InnerResponseDTO> list;


}

