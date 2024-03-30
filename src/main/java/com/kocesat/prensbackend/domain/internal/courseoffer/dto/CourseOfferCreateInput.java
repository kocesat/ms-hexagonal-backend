package com.kocesat.prensbackend.domain.internal.courseoffer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseOfferCreateInput {

  @Size(min = 6, message = "courseCode should be min 6 characters")
  @NotBlank(message = "courseCode cannot be null")
  private String courseCode;

  private String instructor;

  @NotNull(message = "year cannot be null")
  private Integer year;

  @NotNull(message = "semester cannot be null")
  private Integer semester;

  @NotNull(message = "quota cannot be null")
  private Integer quota;

  @NotNull(message = "price cannot be null")
  private BigDecimal price;
}
