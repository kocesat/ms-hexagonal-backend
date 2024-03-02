package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.payment.model.EnrollmentPayment;

public interface EnrollmentPaymentDbPort {

  EnrollmentPayment save(EnrollmentPayment enrollmentPayment);
}
