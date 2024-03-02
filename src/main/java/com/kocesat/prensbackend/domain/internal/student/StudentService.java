package com.kocesat.prensbackend.domain.internal.student;

import com.kocesat.prensbackend.domain.internal.student.model.Student;
import com.kocesat.prensbackend.domain.ports.StudentDbPort;
import com.kocesat.prensbackend.domain.ports.StudentUseCasePort;
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
  public List<Student> getStudents() {
    return studentDbPort.findAll();
  }

  @Override
  public Student getStudentById(Integer studentId) {
    return studentDbPort.findById(studentId)
        .orElseThrow(() -> new IllegalArgumentException("Student not found"));
  }
}
