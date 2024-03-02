package com.kocesat.prensbackend.infra.payment;

import com.kocesat.prensbackend.domain.internal.payment.model.EnrollmentPayment;
import com.kocesat.prensbackend.domain.ports.EnrollmentPaymentDbPort;
import com.kocesat.prensbackend.infra.payment.model.EnrollmentPaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentPaymentDbAdapter implements EnrollmentPaymentDbPort {

  private final EnrollmentPaymentMapper mapper;

  @Override
  public EnrollmentPayment save(EnrollmentPayment enrollmentPayment) {
    EnrollmentPaymentEntity entity = EnrollmentPaymentEntity.from(enrollmentPayment);
    mapper.insert(entity);
    return entity.toDomainModel();
  }
}
