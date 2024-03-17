package com.kocesat.prensbackend.domain.internal.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedException extends BaseException {

  public UnexpectedException(String errorMsg) {
    super(ApiError.builder()
        .code("P-999")
        .message(errorMsg)
        .build());
  }

  public UnexpectedException(ApiError apiError) {
    super(apiError);
  }
}
