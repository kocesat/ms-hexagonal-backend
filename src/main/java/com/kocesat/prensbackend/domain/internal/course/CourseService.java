package com.kocesat.prensbackend.domain.internal.course;

import com.kocesat.prensbackend.domain.internal.common.exception.NotFoundException;
import com.kocesat.prensbackend.domain.ports.CourseDbPort;
import com.kocesat.prensbackend.domain.ports.CourseUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
class CourseService implements CourseUseCasePort {

  private final CourseDbPort courseDbPort;

  @Override
  public List<Course> getCourses() {
    return courseDbPort.findAll();
  }

  @Override
  public Course getCourseByCode(String code) {
    return courseDbPort.findByCode(code)
        .orElseThrow(() -> new NotFoundException(CourseError.NOT_FOUND));
  }
}
