package com.cloud.springboot.service.impl;

import com.cloud.springboot.dao.entity.PayLog;
import com.cloud.springboot.dao.mapper.mapper2.PayLogMapper2;
import com.cloud.springboot.service.PayLogService2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ Description   :  PayLogService2Impl
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-27 10:25
 */
@Service
public class PayLogService2Impl implements PayLogService2 {
    @Resource
    private PayLogMapper2 payLogMapper2;

    @Transactional(value = "test2TransactionManager",readOnly=true)
    @Override
    public PayLog selectOne(String orderNo) {
        return payLogMapper2.selectOne(orderNo);
    }
}
