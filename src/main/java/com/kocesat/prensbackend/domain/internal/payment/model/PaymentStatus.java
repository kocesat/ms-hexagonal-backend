package com.kocesat.prensbackend.domain.internal.payment.model;

import java.util.Arrays;

public enum PaymentStatus {

  INITIAL(10),
  FAIL(99),
  SUCCESS(100);

  private final int code;

  PaymentStatus(int code) {
    this.code = code;
  }

  public static PaymentStatus parse(Integer code) {
    return Arrays.stream(values())
        .filter(s -> s.code == code)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid code for PaymentStatus"));
  }

  public int getCode() {
    return code;
  }
}
