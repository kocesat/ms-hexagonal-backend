package com.kocesat.prensbackend.domain.internal;

 public class NotFoundException extends RuntimeException {

   public NotFoundException() {
   }

   public NotFoundException(String notFountObject) {
     super(notFountObject + " not found!");
   }
 }
