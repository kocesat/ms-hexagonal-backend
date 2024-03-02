package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.payment.model.PaymentResult;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentUseCasePort {

  List<PaymentResult> makePayment(
      Integer studentId, List<Integer> enrollmentIdList, BigDecimal amount);
}
