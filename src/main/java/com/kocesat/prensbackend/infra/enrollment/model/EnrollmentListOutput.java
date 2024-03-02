package com.kocesat.prensbackend.infra.enrollment.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EnrollmentListOutput {

  @Builder.Default
  List<EnrollmentDto> enrollmentList = new ArrayList<>();
}
