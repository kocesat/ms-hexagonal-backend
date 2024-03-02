package com.kocesat.prensbackend.infra.courseoffer;

import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;
import com.kocesat.prensbackend.domain.ports.CourseOfferDbPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
class CourseOfferDbAdapter implements CourseOfferDbPort {

  private final CourseOfferMapper courseOfferMapper;

  @Override
  public Integer countByFilter(CourseOfferFilter filter) {
    return courseOfferMapper.countByFilter(filter);
  }

  @Override
  public List<CourseOffer> findByFilter(CourseOfferFilter filter) {
    final int offset = (filter.getPage() - 1) * filter.getPageSize();
    filter.setOffset(offset);
    return courseOfferMapper.selectByFilter(filter);
  }

  @Override
  public int create(CourseOffer courseOffer) {
    return courseOfferMapper.insert(courseOffer);
  }

  @Override
  public Optional<CourseOffer> findById(Integer courseOfferId) {
    List<CourseOffer> courseOffers = courseOfferMapper.selectById(courseOfferId);
    if (courseOffers == null || courseOffers.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(courseOffers.get(0));
  }

  @Override
  public int updateCountRegistered(CourseOffer courseOffer) {
    return courseOfferMapper.updateCountRegistered(courseOffer);
  }
}
