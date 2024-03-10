package com.kocesat.prensbackend.infra.enrollment.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EnrollmentListOutput {
  private Integer count;
  @Builder.Default
  List<EnrollmentDto> results = new ArrayList<>();
}
