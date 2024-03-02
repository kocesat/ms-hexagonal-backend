package com.kocesat.prensbackend.domain.internal.course;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

  private String code;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
  private String name;
  private String departmentCode;
  private String description;
}
