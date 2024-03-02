package com.kocesat.prensbackend.infra.payment.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PaymentMakeInput {

  @NotNull
  @NotEmpty
  private List<Integer> enrollmentIdList;
  @NotNull
  private Integer studentId;
  @NotNull
  private BigDecimal amount;
}
