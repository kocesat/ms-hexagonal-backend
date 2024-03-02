package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;

import java.util.List;
import java.util.Optional;

public interface EnrollmentUseCasePort {

  Enrollment create(EnrollmentCreateDto dto);

  List<Enrollment> getEnrollments(Integer studentId, Integer year, Integer semester);

  Optional<Enrollment> getEnrollmentById(Integer enrollmentId);

  void updateStatus(Integer id, EnrollmentStatus newStatus);
}
