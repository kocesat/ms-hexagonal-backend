package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;

import java.util.List;
import java.util.Optional;

public interface CourseOfferDbPort {

  Integer countByFilter(CourseOfferFilter filter);

  List<CourseOffer> findByFilter(CourseOfferFilter filter);

  int create(CourseOffer courseOffer);

  Optional<CourseOffer> findById(Integer courseOfferId);

  int updateCountRegistered(CourseOffer courseOffer);
}
