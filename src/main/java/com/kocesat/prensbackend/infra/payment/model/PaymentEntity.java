package com.kocesat.prensbackend.infra.payment.model;

import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

  private Integer id;
  private Integer studentId;
  private Integer status;
  private BigDecimal amount;
  private String paymentProvider;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;

  public static PaymentEntity from(Payment payment) {
    return PaymentEntity.builder()
        .studentId(payment.getStudentId())
        .amount(payment.getAmount())
        .status(payment.getStatus().getCode())
        .paymentProvider(payment.getPaymentProvider())
        .insertTime(payment.getInsertTime())
        .updateTime(payment.getUpdateTime())
        .build();
  }

  public Payment toDomainModel() {
    return Payment.builder()
        .id(id)
        .enrollments(Collections.emptyList())
        .status(PaymentStatus.parse(status))
        .insertTime(insertTime)
        .updateTime(updateTime)
        .amount(amount)
        .studentId(studentId)
        .paymentProvider(paymentProvider)
        .build();
  }
}
