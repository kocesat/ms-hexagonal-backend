package com.kocesat.prensbackend.domain.internal.courseoffer;

import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferCreateInput;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferListOutput;
import com.kocesat.prensbackend.domain.ports.CourseOfferDbPort;
import com.kocesat.prensbackend.domain.ports.CourseOfferUseCasePort;
import com.kocesat.prensbackend.domain.ports.CourseUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseOfferService implements CourseOfferUseCasePort {

  private final CourseOfferDbPort courseOfferDbPort;
  private final CourseUseCasePort courseUseCasePort;

  @Override
  public CourseOfferListOutput getCourseOffers(CourseOfferFilter filter) {
    if (filter == null
        || filter.getYear() == null
        || filter.getSemester() == null) {
      throw new IllegalArgumentException("Invalid CourseOfferFilter");
    }

    List<CourseOffer> courseOffers = courseOfferDbPort.findByFilter(filter);
    return CourseOfferListOutput.builder()
        .totalCount(courseOfferDbPort.countByFilter(filter))
        .courseOffers(courseOffers)
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public CourseOffer getById(Integer courseOfferId) {
    return courseOfferDbPort.findById(courseOfferId)
        .orElseThrow(() -> new IllegalArgumentException("Course offer not found!"));
  }

  @Override
  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
  public void create(CourseOfferCreateInput input) {
    courseUseCasePort.getCourseByCode(input.getCourseCode());

    final int courseOfferCount = courseOfferDbPort.countByFilter(CourseOfferFilter.builder()
        .year(input.getYear())
        .semester(input.getSemester())
        .courseCode(input.getCourseCode())
        .build());

    if (courseOfferCount > 0) {
      throw new IllegalStateException("Course already registered in the semester");
    }

    final CourseOffer courseOffer = CourseOffer.builder()
        .courseCode(input.getCourseCode())
        .instructor(input.getInstructor())
        .quota(input.getQuota())
        .price(input.getPrice())
        .insertTime(LocalDateTime.now())
        .updateTime(LocalDateTime.now())
        .year(input.getYear())
        .semester(input.getSemester())
        .countRegistered(0)
        .build();

    int insertedCount =  courseOfferDbPort.create(courseOffer);
    if (insertedCount != 1) {
      throw new IllegalStateException("Could not create course offer");
    }
  }

  @Override
  public void incrementCountRegistered(CourseOffer courseOffer) {
    courseOffer.setCountRegistered(courseOffer.getCountRegistered() + 1);
    final int updateCount = courseOfferDbPort.updateCountRegistered(courseOffer);
    if (updateCount < 1) {
      throw new IllegalStateException("Update Course offer is not successfull");
    }
    return;
  }
}
