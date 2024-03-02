package com.kocesat.prensbackend.domain.internal.payment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResult {

  private Integer enrollmentId;
  private Boolean success;
}
