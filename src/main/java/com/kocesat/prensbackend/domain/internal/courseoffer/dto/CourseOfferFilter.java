package com.kocesat.prensbackend.domain.internal.courseoffer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseOfferFilter {

  private Integer year;
  private Integer semester;
  private String courseCode;
  private String departmentCode;
  private Integer page;
  private Integer pageSize;
  private Integer offset;
}
