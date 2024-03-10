package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.student.model.Student;
import com.kocesat.prensbackend.infra.base.ListResponse;


public interface StudentUseCasePort {

  ListResponse<Student> getStudents();

  Student getStudentById(Integer studentId);
}
