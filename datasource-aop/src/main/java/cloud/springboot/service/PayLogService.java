package cloud.springboot.service;


import cloud.springboot.dao.entity.PayLog;

public interface PayLogService {
    PayLog selectOne1(String orderNo);
}
