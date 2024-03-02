package com.kocesat.prensbackend.infra.enrollment.model;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity {

  private Integer id;
  private Integer courseOfferId;
  private String courseCode;
  private String courseName;
  private Integer studentId;
  private Integer year;
  private Integer semester;
  private Integer status;
  private BigDecimal paymentAmount;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;

  public Enrollment toDomainModel() {
    return Enrollment.builder()
        .id(id)
        .courseOfferId(courseOfferId)
        .courseCode(courseCode)
        .courseName(courseName)
        .studentId(studentId)
        .insertTime(insertTime)
        .updateTime(updateTime)
        .year(year)
        .paymentAmount(paymentAmount)
        .semester(semester)
        .status(EnrollmentStatus.parse(status))
        .build();
  }
}