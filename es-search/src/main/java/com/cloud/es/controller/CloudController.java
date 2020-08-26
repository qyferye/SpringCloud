package com.cloud.es.controller;


import com.cloud.es.entity.Cloud;
import com.cloud.es.service.CloudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/cloud")
public class CloudController {
    /**
     * 实现Repository 接口形式
     * */
    @Resource
    private CloudService cloudService;


    /**
     * 增
     * */
    @GetMapping("/add")
    public String add(){
        return cloudService.add();
    }

    /**
     * 删
     * */
    @GetMapping("/del")
    public String del(){
        return  cloudService.del();
    }

    /**
     * 改
     * */
    @GetMapping("/update")
    public String update(){
        return  "";
    }

    /**
     * 查
     * */
    @GetMapping("/getByName")
    public List<Cloud> getByName(@PathParam("name") String name){
        return  cloudService.getByName(name);
    }


    /**
     * 条件过滤
     * */
    @GetMapping("/filter")
    public List<Cloud> filter(){
        return  cloudService.filter();
    }



}
