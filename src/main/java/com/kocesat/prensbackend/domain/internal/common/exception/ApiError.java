package com.kocesat.prensbackend.domain.internal.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  private String code;
  private String message;
  private HashMap<String, String> fieldErrors;
}