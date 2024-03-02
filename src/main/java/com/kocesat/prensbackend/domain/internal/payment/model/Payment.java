package com.kocesat.prensbackend.domain.internal.payment.model;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Payment {

  private Integer id;
  private Integer studentId;
  private List<Enrollment> enrollments;
  private PaymentStatus status;
  private BigDecimal amount;
  private String paymentProvider;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
}
