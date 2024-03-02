package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;

import java.util.List;

public interface EnrollmentUseCasePort {

  Enrollment create(EnrollmentCreateDto dto);

  List<Enrollment> getEnrollments(Integer studentId, Integer year, Integer semester);
}
