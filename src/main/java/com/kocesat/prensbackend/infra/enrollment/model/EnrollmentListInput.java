package com.kocesat.prensbackend.infra.enrollment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentListInput {

  private Integer studentId;
  private Integer year;
  private Integer semester;
}
