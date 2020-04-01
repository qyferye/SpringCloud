package com.cloud.springboot.controller;

import com.cloud.springboot.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  AopController
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-26 15:45
 */
@RestController
@RequestMapping("/aspect")
public class AopController {
    @Autowired
    private CalcService calcService;

    @PostMapping(value = "/aop")
    public String storePerformancePage(@RequestBody String json)  {
        calcService.add(1,2);
        return json;
    }
}
