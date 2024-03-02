package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.course.Course;

import java.util.List;

public interface CourseUseCasePort {

  List<Course> getCourses();

  Course getCourseByCode(String code);
}
