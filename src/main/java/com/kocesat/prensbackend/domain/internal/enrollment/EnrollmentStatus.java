package com.kocesat.prensbackend.domain.internal.enrollment;

import java.util.Arrays;

public enum EnrollmentStatus {

  RESERVED(0),
  PAYMENT_IN_PROCESS(10),
  PAYMENT_FAILED(20),
  TECHNICAL_ERROR(29),
  SUCCESSFUL(100);

  private final int code;

  EnrollmentStatus(int code) {
    this.code = code;
  }

  public static EnrollmentStatus parse(int code) {
    return Arrays.stream(values())
        .filter(e -> e.code == code)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Invalid code for EnrollmentStatus"));
  }

  public int getCode() {
    return code;
  }
}
