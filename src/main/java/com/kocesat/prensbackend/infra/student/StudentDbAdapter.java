package com.kocesat.prensbackend.infra.student;

import com.kocesat.prensbackend.domain.internal.student.model.Student;
import com.kocesat.prensbackend.domain.ports.StudentDbPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
class StudentDbAdapter implements StudentDbPort {

  private final StudentMapper studentMapper;

  @Override
  public List<Student> findAll() {
    return studentMapper.selectAll();
  }

  @Override
  public Optional<Student> findById(Integer studentId) {
    List<Student> students = studentMapper.selectById(studentId);
    if (students == null || students.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(students.get(0));
  }
}
