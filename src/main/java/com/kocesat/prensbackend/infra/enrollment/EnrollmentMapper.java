package com.kocesat.prensbackend.infra.enrollment;

import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
interface EnrollmentMapper {

  List<EnrollmentEntity> select(@Param("studentId") Integer studentId,
                                @Param("year") Integer year,
                                @Param("semester") Integer semester);

  int insert(EnrollmentEntity enrollment);
}
