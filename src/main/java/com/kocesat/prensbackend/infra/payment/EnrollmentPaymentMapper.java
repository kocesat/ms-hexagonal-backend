package com.kocesat.prensbackend.infra.payment;

import com.kocesat.prensbackend.infra.payment.model.EnrollmentPaymentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentPaymentMapper {

  int insert(EnrollmentPaymentEntity enrollmentPayment);
}
