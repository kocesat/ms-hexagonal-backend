package com.kocesat.prensbackend.infra.enrollment;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentFilter;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import com.kocesat.prensbackend.domain.ports.EnrollmentDbPort;
import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class EnrollmentDbAdapter implements EnrollmentDbPort {

  private final EnrollmentMapper enrollmentMapper;

  @Override
  public List<Enrollment> findByFilter(EnrollmentFilter filter) {
    List<EnrollmentEntity> entities = enrollmentMapper.select(
        filter.getStudentId(),
        filter.getYear(),
        filter.getSemester());

    return entities.stream()
        .map(EnrollmentEntity::toDomainModel)
        .toList();
  }

  @Override
  public Optional<Enrollment> findById(Integer id) {
    List<EnrollmentEntity> enrollments = enrollmentMapper.selectById(id);
    if (enrollments.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(enrollments.get(0).toDomainModel());
  }


  @Override
  public Enrollment saveEnrollment(EnrollmentCreateDto dto) {
    EnrollmentEntity entity = EnrollmentEntity.builder()
        .courseOfferId(dto.getCourseOfferId())
        .studentId(dto.getStudentId())
        .year(dto.getYear())
        .semester(dto.getSemester())
        .status(EnrollmentStatus.RESERVED.getCode())
        .build();

    int insertedCount = enrollmentMapper.insert(entity);
    if (insertedCount < 1) {
      throw new IllegalStateException("Fail to insert!");
    }

    return entity.toDomainModel();
  }

  @Override
  public int updateStatus(Integer id, EnrollmentStatus newStatus) {
    return enrollmentMapper.updateStatus(id, newStatus.getCode());
  }
}