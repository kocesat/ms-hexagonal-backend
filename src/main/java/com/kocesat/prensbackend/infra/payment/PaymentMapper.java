package com.kocesat.prensbackend.infra.payment;

import com.kocesat.prensbackend.infra.payment.model.PaymentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {

  List<PaymentEntity> selectById(@Param("id") Integer id);

  int insert(PaymentEntity payment);

  int updateStatus(Integer id, Integer newStatus);
}
