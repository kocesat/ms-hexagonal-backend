package com.kocesat.prensbackend.infra.course;

import com.kocesat.prensbackend.domain.internal.course.Course;
import com.kocesat.prensbackend.domain.ports.CourseDbPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
class CourseDbAdapter implements CourseDbPort {

  private final CourseMapper courseMapper;

  @Override
  public List<Course> findAll() {
    return courseMapper.selectAll();
  }

  @Override
  public Optional<Course> findByCode(String code) {
    List<Course> courseList = courseMapper.selectByCode(code);
    if (courseList.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(courseList.get(0));
  }
}
