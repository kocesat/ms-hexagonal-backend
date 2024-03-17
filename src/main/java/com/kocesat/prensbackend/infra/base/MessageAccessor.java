package com.kocesat.prensbackend.infra.base;

import com.kocesat.prensbackend.domain.internal.common.constant.ErrorConstant;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageAccessor {
  private static final String DEFAULT_ERROR_MSG =
      "We are not able to process your transaction temporarily. Please try again later";
  private static MessageSource messageSource;

  public MessageAccessor(MessageSource messageSource) {
    MessageAccessor.messageSource = messageSource;
  }

  public static String getMessage(String key, Locale locale) {
    if (key == null) {
      key = ErrorConstant.DEFAULT;
    }
    try {
      return messageSource.getMessage(key, new Object[0], locale);
    } catch (NoSuchMessageException e) {
      return DEFAULT_ERROR_MSG;
    }
  }
}