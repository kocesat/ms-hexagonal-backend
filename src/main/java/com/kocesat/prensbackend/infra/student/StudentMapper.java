package com.kocesat.prensbackend.infra.student;

import com.kocesat.prensbackend.domain.internal.student.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

  List<Student> selectAll();

  List<Student> selectById(@Param("studentId") Integer studentId);
}
