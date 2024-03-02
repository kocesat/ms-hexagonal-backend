package com.kocesat.prensbackend.domain.internal.payment;

public interface PaymentProviderPort {

  Boolean makePayment(boolean isSuccess);
}
