package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentStatus;

public interface PaymentDbPort {

//  Optional<Payment> findById(Integer id);
  Payment save(Payment payment);
  int update(Integer id, PaymentStatus newStatus);
}
