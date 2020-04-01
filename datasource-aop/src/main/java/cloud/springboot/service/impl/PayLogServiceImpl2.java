package cloud.springboot.service.impl;


import cloud.springboot.annotions.DbSource;
import cloud.springboot.dao.entity.PayLog;
import cloud.springboot.dao.mapper.PayLogMapper;
import cloud.springboot.service.PayLogService2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Description   :  PayLogService1Impl
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-27 10:25
 */
@Service
@DbSource("READ_TEST2")
public class PayLogServiceImpl2 implements PayLogService2 {
    @Resource
    private PayLogMapper payLogMapper;
    @Override
    public PayLog selectOne2(String orderNo) {
        return payLogMapper.selectOne(orderNo);
    }
}
