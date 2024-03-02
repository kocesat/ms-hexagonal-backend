package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDbPort {

  List<Student> findAll();

  Optional<Student> findById(Integer studentId);
}
