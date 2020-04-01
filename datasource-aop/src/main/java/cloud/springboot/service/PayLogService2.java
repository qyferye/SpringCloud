package cloud.springboot.service;


import cloud.springboot.dao.entity.PayLog;

public interface PayLogService2 {
    PayLog selectOne2(String orderNo);
}
