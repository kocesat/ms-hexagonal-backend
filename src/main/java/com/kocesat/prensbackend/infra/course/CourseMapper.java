package com.kocesat.prensbackend.infra.course;

import com.kocesat.prensbackend.domain.internal.course.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
interface CourseMapper {

  List<Course> selectAll();

  List<Course> selectByCode(String code);
}
