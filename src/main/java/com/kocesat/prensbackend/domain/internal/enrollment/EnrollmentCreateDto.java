package com.kocesat.prensbackend.domain.internal.enrollment;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentCreateDto {

  @NotNull
  private Integer courseOfferId;
  @NotNull
  private Integer studentId;
  @NotNull
  private Integer year;
  @NotNull
  private Integer semester;
}
