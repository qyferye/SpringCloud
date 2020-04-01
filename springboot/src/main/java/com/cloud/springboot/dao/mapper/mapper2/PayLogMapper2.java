package com.cloud.springboot.dao.mapper.mapper2;


import com.cloud.springboot.dao.entity.PayLog;
import org.apache.ibatis.annotations.Param;

public interface PayLogMapper2 {
  PayLog selectOne(@Param("orderNo") String orderNo);
}