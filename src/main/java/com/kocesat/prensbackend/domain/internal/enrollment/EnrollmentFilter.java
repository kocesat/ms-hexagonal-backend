package com.kocesat.prensbackend.domain.internal.enrollment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentFilter {

  private Integer studentId;
  private Integer year;
  private Integer semester;
}
