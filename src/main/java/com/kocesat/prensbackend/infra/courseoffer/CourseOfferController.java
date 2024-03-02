package com.kocesat.prensbackend.infra.courseoffer;

import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferCreateInput;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferListOutput;
import com.kocesat.prensbackend.domain.ports.CourseOfferUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-offer")
@RequiredArgsConstructor
class CourseOfferController {

  private final CourseOfferUseCasePort courseOfferUseCasePort;

  @PostMapping()
  public CourseOfferListOutput getCourseOffer(@Valid @RequestBody CourseOfferFilter filter) {
    return courseOfferUseCasePort.getCourseOffers(filter);
  }

  @PostMapping("create")
  public ResponseEntity<Void> create(@Valid @RequestBody CourseOfferCreateInput input) {
    courseOfferUseCasePort.create(input);
    return ResponseEntity.created(null).build();
  }
}
