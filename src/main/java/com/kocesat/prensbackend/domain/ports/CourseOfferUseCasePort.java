package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferCreateInput;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferListOutput;

public interface CourseOfferUseCasePort {

  CourseOfferListOutput getCourseOffers(CourseOfferFilter filter);

  CourseOffer getById(Integer courseOfferId);

  void create(CourseOfferCreateInput input);

  void incrementCountRegistered(CourseOffer courseOffer);
}
