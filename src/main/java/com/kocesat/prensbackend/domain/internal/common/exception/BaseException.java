package com.kocesat.prensbackend.domain.internal.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseException extends RuntimeException {

  private ApiError apiError;
}
