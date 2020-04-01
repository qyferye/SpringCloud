package com.cloud.springboot.service.impl;

import com.cloud.springboot.dao.entity.PayLog;
import com.cloud.springboot.dao.mapper.mapper1.PayLogMapper1;
import com.cloud.springboot.service.PayLogService1;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ Description   :  PayLogService1Impl
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-27 10:25
 */
@Service
public class PayLogService1Impl implements PayLogService1 {
    @Resource
    private PayLogMapper1 payLogMapper1;

    @Transactional(value = "test1TransactionManager",readOnly=true)
    @Override
    public PayLog selectOne(String orderNo) {
        return payLogMapper1.selectOne(orderNo);
    }
}
