package com.kocesat.prensbackend.infra.course;

import com.kocesat.prensbackend.domain.internal.course.Course;
import com.kocesat.prensbackend.domain.ports.CourseUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
class CourseController {

  private final CourseUseCasePort courseUseCasePort;

  @GetMapping
  public List<Course> getCourses() {
    return courseUseCasePort.getCourses();
  }
}
