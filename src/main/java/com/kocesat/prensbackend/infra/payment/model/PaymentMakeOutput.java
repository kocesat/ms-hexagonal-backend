package com.kocesat.prensbackend.infra.payment.model;

import com.kocesat.prensbackend.domain.internal.payment.model.PaymentResult;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaymentMakeOutput {

  private List<PaymentResult> paymentResultList;
}
