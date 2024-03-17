package com.kocesat.prensbackend.domain.internal.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
 public class NotFoundException extends BaseException {

  public NotFoundException(ApiError apiError) {
    super(apiError);
  }

  public NotFoundException(String errorCode) {
    super(ApiError.builder()
        .code(errorCode)
        .build());
  }
}
