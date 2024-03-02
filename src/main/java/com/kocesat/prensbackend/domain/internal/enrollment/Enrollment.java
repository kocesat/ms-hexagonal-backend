package com.kocesat.prensbackend.domain.internal.enrollment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Enrollment {

  private Integer id;
  private Integer courseOfferId;
  private String courseCode;
  private String courseName;
  private Integer studentId;
  private Integer year;
  private Integer semester;
  private EnrollmentStatus status;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
}
