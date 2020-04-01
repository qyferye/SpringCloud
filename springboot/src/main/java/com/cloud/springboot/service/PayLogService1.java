package com.cloud.springboot.service;

import com.cloud.springboot.dao.entity.PayLog;

public interface PayLogService1 {
    PayLog selectOne(String orderNo);
}
