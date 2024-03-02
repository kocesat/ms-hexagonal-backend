package com.kocesat.prensbackend.infra.enrollment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDto {

  private Integer year;
  private Integer semester;
  private Integer studentId;
  private String courseCode;
  private String courseName;
  private EnrollmentStatus enrollmentStatus;

  public static EnrollmentDto from(Enrollment enrollment) {
    return EnrollmentDto.builder()
        .year(enrollment.getYear())
        .semester(enrollment.getSemester())
        .studentId(enrollment.getStudentId())
        .courseCode(enrollment.getCourseCode())
        .courseName(enrollment.getCourseName())
        .enrollmentStatus(enrollment.getStatus())
        .build();
  }
}
