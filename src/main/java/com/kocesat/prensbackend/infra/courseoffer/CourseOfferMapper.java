package com.kocesat.prensbackend.infra.courseoffer;

import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import com.kocesat.prensbackend.domain.internal.courseoffer.dto.CourseOfferFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
interface CourseOfferMapper {

  List<CourseOffer> selectByFilter(CourseOfferFilter filter);

  Integer countByFilter(CourseOfferFilter filter);

  List<CourseOffer> selectById(Integer courseOfferId);

  int insert(CourseOffer courseOffer);

  int updateCountRegistered(CourseOffer courseOffer);
}
