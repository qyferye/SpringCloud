package cloud.springboot.dao.mapper;


import cloud.springboot.dao.entity.PayLog;
import org.apache.ibatis.annotations.Param;

public interface PayLogMapper {
  PayLog selectOne(@Param("orderNo") String orderNo);
}