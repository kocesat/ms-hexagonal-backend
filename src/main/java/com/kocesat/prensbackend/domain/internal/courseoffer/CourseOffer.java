package com.kocesat.prensbackend.domain.internal.courseoffer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseOffer {

  private Integer id;
  private String courseCode;
  private String courseName;
  private String instructor;
  private String departmentCode;
  private Integer year;
  private Integer semester;
  private Integer quota;
  private Integer countRegistered;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
  private BigDecimal price;

  public boolean hasCapacity() {
    return (quota - countRegistered) > 0;
  }

  public boolean inSameSemester(int year, int semester) {
    return this.year.equals(year) && this.semester.equals(semester);
  }
}
