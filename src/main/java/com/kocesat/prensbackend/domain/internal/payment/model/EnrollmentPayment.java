package com.kocesat.prensbackend.domain.internal.payment.model;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentPayment {

  private Integer id;
  private Payment payment;
  private Enrollment enrollment;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
}
