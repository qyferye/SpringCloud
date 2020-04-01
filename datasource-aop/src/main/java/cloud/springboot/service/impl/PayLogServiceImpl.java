package cloud.springboot.service.impl;


import cloud.springboot.annotions.DbSource;
import cloud.springboot.dao.entity.PayLog;
import cloud.springboot.dao.mapper.PayLogMapper;
import cloud.springboot.service.PayLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Description   :  PayLogService1Impl
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-27 10:25
 */
@Service
@DbSource("READ_TEST1")
public class PayLogServiceImpl implements PayLogService {
    @Resource
    private PayLogMapper payLogMapper;

    @Override
    public PayLog selectOne1(String orderNo) {
        return payLogMapper.selectOne(orderNo);
    }
}
