package com.kocesat.prensbackend.infra.enrollment;

import com.kocesat.prensbackend.domain.internal.enrollment.Enrollment;
import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentCreateDto;
import com.kocesat.prensbackend.domain.ports.EnrollmentUseCasePort;
import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentDto;
import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentListInput;
import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentListOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

  private final EnrollmentUseCasePort enrollmentUseCasePort;

  @PostMapping("/list")
  public EnrollmentListOutput list(@RequestBody @Valid EnrollmentListInput input) {
    List<Enrollment> enrollments = enrollmentUseCasePort.getEnrollments(
        input.getStudentId(), input.getYear(), input.getSemester());
    List<EnrollmentDto> enrollmentDtos = enrollments.stream()
        .map(EnrollmentDto::from)
        .toList();
    return EnrollmentListOutput.builder()
        .enrollmentList(enrollmentDtos)
        .build();
  }

  @PostMapping
  public Enrollment create(@RequestBody @Valid EnrollmentCreateDto dto) {
    return enrollmentUseCasePort.create(dto);
  }


}
