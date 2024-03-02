package com.kocesat.prensbackend.domain.internal.payment;

import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import com.kocesat.prensbackend.domain.internal.payment.model.EnrollmentPayment;
import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentStatus;
import com.kocesat.prensbackend.domain.ports.EnrollmentPaymentDbPort;
import com.kocesat.prensbackend.domain.ports.EnrollmentUseCasePort;
import com.kocesat.prensbackend.domain.ports.PaymentDbPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(
    rollbackFor = Exception.class,
    isolation = Isolation.REPEATABLE_READ,
    propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
@Service
class EnrollmentPaymentService {

  private final PaymentDbPort paymentDbPort;
  private final EnrollmentPaymentDbPort enrollmentPaymentDbPort;
  private final EnrollmentUseCasePort enrollmentUseCasePort;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public EnrollmentPayment insertInNewTx(EnrollmentPayment enrollmentPayment) {
    final Payment payment = paymentDbPort.save(enrollmentPayment.getPayment());
    if (payment.getId() == null) {
      throw new IllegalStateException("Could not get paymentId while inserting!!!");
    }

    enrollmentPayment.getPayment().setId(payment.getId());

    final EnrollmentPayment enrollmentPaymentSaved = enrollmentPaymentDbPort.save(enrollmentPayment);
    enrollmentPayment.setId(enrollmentPaymentSaved.getId());
    return enrollmentPayment;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void updateEnrollmentStatusInNewTx(
      Integer paymentId, PaymentStatus newPaymentStatus,
      Integer enrollmentId, EnrollmentStatus newEnrollmentStatus
  ) {
    updatePaymentStatus(paymentId, newPaymentStatus);
    enrollmentUseCasePort.updateStatus(enrollmentId, newEnrollmentStatus);
  }


  private void updatePaymentStatus(Integer id, PaymentStatus newStatus) {
    final int updatedCount = paymentDbPort.update(id, newStatus);
    if (updatedCount < 1) {
      throw new IllegalStateException("Payment update failed!");
    }
  }
}
