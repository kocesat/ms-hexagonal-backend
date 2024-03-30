package com.kocesat.prensbackend.infra.web;

import com.kocesat.prensbackend.domain.internal.common.constant.ErrorConstant;
import com.kocesat.prensbackend.domain.internal.common.exception.ApiError;
import com.kocesat.prensbackend.domain.internal.common.exception.BaseException;
import com.kocesat.prensbackend.infra.base.MessageAccessor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ApiError> handleBaseException(BaseException e) {
    final var apiError = e.getApiError();
    Annotation[] annotations = e.getClass().getAnnotations();
    int httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    for (Annotation annotation : annotations) {
      if (annotation.annotationType() == ResponseStatus.class) {
        HttpStatus httpStatus = ((ResponseStatus) annotation).value();
        if (httpStatus != null) {
          httpStatusCode = httpStatus.value();
        }
      }
    }
    if (apiError.getCode() == null) {
      apiError.setCode(ErrorConstant.DEFAULT);
    }
    if (apiError.getMessage() == null) {
      Locale locale = LocaleContextHolder.getLocale();
      final String errorCode = apiError.getCode();
      String localizedMsg = MessageAccessor.getMessage(errorCode, locale);
      apiError.setMessage(localizedMsg);
    }
    return ResponseEntity.status(httpStatusCode).body(apiError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgsError(MethodArgumentNotValidException e) {
    final var apiError = ApiError.builder()
        .code(ErrorConstant.DEFAULT)
        .message("Api field errors occured!")
        .fieldErrors(new HashMap<>())
        .build();

    List<FieldError> fieldErrors = e.getFieldErrors();

    for (FieldError error : fieldErrors) {
      apiError.getFieldErrors().put(error.getField(), error.getDefaultMessage());
    }

    return ResponseEntity.badRequest().body(apiError);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUnhandledException(Exception t) {

    final var apiError = ApiError.builder()
        .code(ErrorConstant.DEFAULT)
        .message(MessageAccessor.getMessage(
            ErrorConstant.DEFAULT, LocaleContextHolder.getLocale()))
        .build();
    return ResponseEntity.internalServerError().body(apiError);
  }
}