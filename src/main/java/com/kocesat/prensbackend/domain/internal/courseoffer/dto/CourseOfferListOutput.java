package com.kocesat.prensbackend.domain.internal.courseoffer.dto;

import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseOfferListOutput {

  private Integer count;
  private List<CourseOffer> results;
}
