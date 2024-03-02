package com.kocesat.prensbackend.infra.enrollment;

import com.kocesat.prensbackend.domain.internal.enrollment.EnrollmentStatus;
import com.kocesat.prensbackend.infra.enrollment.model.EnrollmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
interface EnrollmentMapper {

  List<EnrollmentEntity> select(@Param("studentId") Integer studentId,
                                @Param("year") Integer year,
                                @Param("semester") Integer semester);

  List<EnrollmentEntity> selectById(@Param("id") Integer id);

  int insert(EnrollmentEntity enrollment);

  int updateStatus(Integer id, Integer newStatus);
}
