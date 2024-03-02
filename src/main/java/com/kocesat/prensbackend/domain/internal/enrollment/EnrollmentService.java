package com.kocesat.prensbackend.domain.internal.enrollment;


import com.kocesat.prensbackend.domain.internal.courseoffer.CourseOffer;
import com.kocesat.prensbackend.domain.ports.CourseOfferUseCasePort;
import com.kocesat.prensbackend.domain.ports.EnrollmentDbPort;
import com.kocesat.prensbackend.domain.ports.EnrollmentUseCasePort;
import com.kocesat.prensbackend.domain.ports.StudentUseCasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnrollmentService implements EnrollmentUseCasePort {

  private final EnrollmentDbPort enrollmentDbPort;
  private final CourseOfferUseCasePort courseOfferUseCasePort;
  private final StudentUseCasePort studentUseCasePort;

  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
  public Enrollment create(EnrollmentCreateDto dto) {
    studentUseCasePort.getStudentById(dto.getStudentId());

    final CourseOffer courseOffer = courseOfferUseCasePort.getById(dto.getCourseOfferId());

    if (!courseOffer.inSameSemester(dto.getYear(), dto.getSemester())) {
      throw new IllegalStateException("Course offer not found");
    }

    if (!courseOffer.hasCapacity()) {
      throw new IllegalStateException("Course capacity is full!");
    }

    List<Enrollment> enrollmentList = enrollmentDbPort.findByFilter(EnrollmentFilter.builder()
        .year(dto.getYear())
        .semester(dto.getSemester())
        .studentId(dto.getStudentId())
        .build());

    final boolean alreadyTaken = enrollmentList.stream()
        .anyMatch(enrollment -> enrollment.getCourseOfferId().equals(dto.getCourseOfferId()));

    if (alreadyTaken) {
      throw new IllegalStateException("Course already taken for the specified semester");
    }

    final Enrollment enrollment = enrollmentDbPort.saveEnrollment(dto);
    courseOfferUseCasePort.incrementCountRegistered(courseOffer);
    return enrollment;
  }

  @Override
  public List<Enrollment> getEnrollments(Integer studentId, Integer year, Integer semester) {
    return enrollmentDbPort.findByFilter(EnrollmentFilter.builder()
        .studentId(studentId)
        .year(year)
        .semester(semester)
        .build());
  }

  @Override
  public Optional<Enrollment> getEnrollmentById(Integer enrollmentId) {
    return enrollmentDbPort.findById(enrollmentId);
  }

  @Override
  public void updateStatus(Integer id, EnrollmentStatus newStatus) {
    int updatedCount = enrollmentDbPort.updateStatus(id, newStatus);

    if (updatedCount < 1) {
      throw new IllegalStateException("Update enrollment failed!");
    }
  }
}
