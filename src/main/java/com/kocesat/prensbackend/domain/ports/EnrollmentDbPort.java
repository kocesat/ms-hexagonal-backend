package com.kocesat.prensbackend.domain.ports;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentFilter;

import java.util.List;

public interface EnrollmentDbPort {

  List<Enrollment> findByFilter(EnrollmentFilter filter);

  Enrollment saveEnrollment(EnrollmentCreateDto dto);
}
