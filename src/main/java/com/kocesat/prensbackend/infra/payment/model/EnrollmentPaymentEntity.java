package com.kocesat.prensbackend.infra.payment.model;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.payment.model.EnrollmentPayment;
import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentPaymentEntity {

  private Integer id;
  private Integer enrollmentId;
  private Integer paymentId;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;

  public static EnrollmentPaymentEntity from(EnrollmentPayment model) {
    return EnrollmentPaymentEntity.builder()
        .enrollmentId(model.getEnrollment().getId())
        .paymentId(model.getPayment().getId())
        .insertTime(model.getInsertTime())
        .updateTime(model.getUpdateTime())
        .build();
  }

  public EnrollmentPayment toDomainModel() {
    return EnrollmentPayment.builder()
        .id(id)
        .payment(Payment.builder().id(paymentId).build())
        .enrollment(Enrollment.builder().id(enrollmentId).build())
        .insertTime(insertTime)
        .updateTime(updateTime)
        .build();
  }
}

