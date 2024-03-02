package com.kocesat.prensbackend.domain.internal.student.model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private Integer id;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
  private String nationalId;
  private String name;
  private String surname;
  private LocalDate birthdate;
}
