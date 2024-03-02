package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.student.model.Student;

import java.util.List;

public interface StudentUseCasePort {

  List<Student> getStudents();

  Student getStudentById(Integer studentId);
}
