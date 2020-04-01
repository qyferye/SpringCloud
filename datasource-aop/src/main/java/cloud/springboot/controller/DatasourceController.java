package cloud.springboot.controller;

import cloud.springboot.dao.entity.PayLog;
import cloud.springboot.service.PayLogService;
import cloud.springboot.service.PayLogService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @ Description   :  DatasourceController
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-27 10:28
 */
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

    @Autowired
    private PayLogService payLogService;
    @Autowired
    private PayLogService2 payLogService2;


    @PostMapping("/datasource1")
    public PayLog selectOneByDatasource1(String orderNo){
        return payLogService.selectOne1(orderNo);
    }
    @PostMapping("/datasource2")
    public PayLog selectOneByDatasource2(String orderNo){
        return payLogService2.selectOne2(orderNo);
    }
}
