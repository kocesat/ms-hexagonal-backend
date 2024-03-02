package com.kocesat.prensbackend.infra.payment;


import com.kocesat.prensbackend.domain.internal.payment.model.PaymentResult;
import com.kocesat.prensbackend.domain.ports.PaymentUseCasePort;
import com.kocesat.prensbackend.infra.payment.model.PaymentMakeInput;
import com.kocesat.prensbackend.infra.payment.model.PaymentMakeOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentUseCasePort paymentUseCasePort;

  @PostMapping("/make")
  public PaymentMakeOutput makePayment(@RequestBody @Valid PaymentMakeInput input) {
    List<PaymentResult> paymentResultList = paymentUseCasePort.makePayment(
        input.getStudentId(),
        input.getEnrollmentIdList(),
        input.getAmount());

    return PaymentMakeOutput.builder()
        .paymentResultList(paymentResultList)
        .build();
  }
}
