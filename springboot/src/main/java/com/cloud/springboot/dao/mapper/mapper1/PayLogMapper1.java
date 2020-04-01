package com.cloud.springboot.dao.mapper.mapper1;


import com.cloud.springboot.dao.entity.PayLog;
import org.apache.ibatis.annotations.Param;

public interface PayLogMapper1 {
  PayLog selectOne(@Param("orderNo") String orderNo);
}