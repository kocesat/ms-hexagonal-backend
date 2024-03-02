package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentFilter;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDbPort {

  List<Enrollment> findByFilter(EnrollmentFilter filter);

  Optional<Enrollment> findById(Integer id);

  Enrollment saveEnrollment(EnrollmentCreateDto dto);

  int updateStatus(Integer id, EnrollmentStatus newStatus);
}
