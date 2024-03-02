package com.kocesat.prensbackend.infra.payment;

import com.kocesat.prensbackend.domain.internal.payment.PaymentProviderPort;
import org.springframework.stereotype.Component;

@Component
public class CrafgatePaymentAdapter implements PaymentProviderPort {

  @Override
  public Boolean makePayment(boolean isSuccess) {
    if (isSuccess) {
      return Boolean.TRUE;
    }

    throw new RuntimeException("Payment failed!");
  }
}
