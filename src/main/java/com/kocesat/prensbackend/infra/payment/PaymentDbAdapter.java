package com.kocesat.prensbackend.infra.payment;

import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentStatus;
import com.kocesat.prensbackend.domain.ports.PaymentDbPort;
import com.kocesat.prensbackend.infra.payment.model.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDbAdapter implements PaymentDbPort {

  private final PaymentMapper paymentMapper;

  @Override
  public Payment save(Payment payment) {
    PaymentEntity paymentEntity = PaymentEntity.from(payment);
    paymentMapper.insert(paymentEntity);
    return paymentEntity.toDomainModel();
  }

  @Override
  public int update(Integer id, PaymentStatus newStatus) {
    return paymentMapper.updateStatus(id, newStatus.getCode());
  }
}
