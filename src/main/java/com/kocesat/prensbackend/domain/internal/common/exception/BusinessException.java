package com.kocesat.prensbackend.domain.internal.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends BaseException {

  public BusinessException(String errorCode) {
    super(ApiError.builder()
        .code(errorCode)
        .build());
  }

  public BusinessException(ApiError apiError) {
    super(apiError);
  }
}
