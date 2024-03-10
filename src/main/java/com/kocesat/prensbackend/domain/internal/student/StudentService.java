package com.kocesat.prensbackend.domain.internal.student;

import com.kocesat.prensbackend.domain.internal.student.model.Student;
import com.kocesat.prensbackend.domain.ports.StudentDbPort;
import com.kocesat.prensbackend.domain.ports.StudentUseCasePort;
import com.kocesat.prensbackend.infra.base.ListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
class StudentService implements StudentUseCasePort {

  private final StudentDbPort studentDbPort;

  @Override
  public ListResponse<Student> getStudents() {
    List<Student> students = studentDbPort.findAll();
    return ListResponse.<Student>builder()
        .count(students.size())
        .results(students)
        .build();
  }

  @Override
  public Student getStudentById(Integer studentId) {
    return studentDbPort.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));
  }
}
