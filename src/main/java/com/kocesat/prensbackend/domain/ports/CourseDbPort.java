package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.course.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDbPort {

  List<Course> findAll();

  Optional<Course> findByCode(String code);
}
