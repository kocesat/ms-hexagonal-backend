package com.kocesat.prensbackend.domain.internal.payment;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import com.kocesat.prensbackend.domain.internal.payment.model.EnrollmentPayment;
import com.kocesat.prensbackend.domain.internal.payment.model.Payment;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentResult;
import com.kocesat.prensbackend.domain.internal.payment.model.PaymentStatus;
import com.kocesat.prensbackend.domain.ports.EnrollmentUseCasePort;
import com.kocesat.prensbackend.domain.ports.PaymentUseCasePort;
import com.kocesat.prensbackend.domain.ports.StudentUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
class PaymentService implements PaymentUseCasePort {

  private final EnrollmentUseCasePort enrollmentUseCasePort;
  private final StudentUseCasePort studentUseCasePort;
  private final EnrollmentPaymentService enrollmentPaymentService;
  private final PaymentProviderPort paymentProviderPort;


  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
  public List<PaymentResult> makePayment(Integer studentId, List<Integer> enrollmentIdList, BigDecimal amount) {
    // check if student exists
    studentUseCasePort.getStudentById(studentId);

    List<PaymentResult> paymentResultList = new ArrayList<>();
    for (Integer enrollmentId : enrollmentIdList) {
      PaymentResult paymentResult = PaymentResult.builder()
          .enrollmentId(enrollmentId)
          .success(makeOnePayment(studentId, enrollmentId, amount))
          .build();
      paymentResultList.add(paymentResult);
    }

    return paymentResultList;
  }

  private boolean makeOnePayment(Integer studentId, Integer enrollmentId, BigDecimal amount) {
    Optional<Enrollment> enrollmentOptional = enrollmentUseCasePort.getEnrollmentById(enrollmentId);

    if (enrollmentOptional.isEmpty()) {
      log.warn("EnrollmentEntity not found!");
      return false;
    }

    final Enrollment enrollment = enrollmentOptional.get();

    if (!enrollment.getStudentId().equals(studentId)) {
      log.warn(String.format("Enrollment: %d is not taken by studentId: %d", enrollmentId, studentId));
      return false;
    }

    if (!enrollment.getStatus().isPayable()) {
      log.warn(String.format("Enrollment: %d is not payable", enrollmentId));
      return false;
    }

    final Payment payment = Payment.builder()
        .status(PaymentStatus.INITIAL)
        .amount(amount)
        .paymentProvider("Craftgate")
        .enrollments(List.of(enrollment))
        .studentId(studentId)
        .build();

    final EnrollmentPayment enrollmentPayment = enrollmentPaymentService.insertInNewTx(
        EnrollmentPayment.builder()
            .enrollment(enrollment)
            .payment(payment)
            .build());

    final Integer paymentId = enrollmentPayment.getPayment().getId();

    try {
      paymentProviderPort.makePayment(true);
    } catch (Exception e) {
      log.error("Payment failed!", e);
      enrollmentPaymentService.updateEnrollmentStatusInNewTx(
          paymentId, PaymentStatus.FAIL, enrollmentId, EnrollmentStatus.PAYMENT_FAILED);
      return false;
    }

    enrollmentPaymentService.updateEnrollmentStatusInNewTx(
        paymentId, PaymentStatus.SUCCESS, enrollmentId, EnrollmentStatus.SUCCESSFUL);
    return true;
  }
}