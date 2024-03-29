package com.kocesat.prensbackend.infra.student;

import com.kocesat.prensbackend.domain.ports.StudentUseCasePort;
import com.kocesat.prensbackend.domain.internal.student.model.Student;
import com.kocesat.prensbackend.infra.base.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
class StudentController {

  private final StudentUseCasePort studentUseCasePort;

  @GetMapping
  public ListResponse<Student> getStudents() {
    return studentUseCasePort.getStudents();
  }

}
