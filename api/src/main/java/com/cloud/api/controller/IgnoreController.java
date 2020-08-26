package com.cloud.api.controller;

import com.cloud.api.test.beantest.SingleBean;
import com.cloud.api.test.beantest.SingleBean2;
import com.cloud.api.test.beantest.SingletonBean;
import com.cloud.core.common.constant.DefaultResultEnum;
import com.cloud.core.common.util.ResultUtil;
import com.cloud.core.dto.DefaultResult;
import com.cloud.core.dto.Result;
import com.cloud.core.dto.User;
import com.cloud.core.exception.BusinessException;
import com.cloud.core.properties.TestPropertySource;
import com.cloud.dao.entity.CloudUser;
import com.cloud.service.CloudUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/ignore")
@Api("不需要验证")
@Slf4j
public class IgnoreController {
    @Autowired
    private CloudUserService cloudUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SingleBean singleBean;
    @Autowired
    private SingleBean2 singleBean2;
    @Autowired
    private SingletonBean singletonBean;

    @ApiOperation(value = "测试入口")
    @PostMapping(value = "/noLogin")
    public Result<String> noLogin(String name) {
        int i = 1 / 0;
        log.info(name);
        return new ResultUtil<String>().setSuccessMsg("欢迎cloud！");
    }
    @ApiOperation(value = "测试singleBean")
    @PostMapping(value = "/singleBean")
    public Result<String> singleBean() {
        System.out.println("测试多例");
        System.out.println(singleBean.getPrototypeBean().equals(singleBean.getPrototypeBean()));
        System.out.println(singleBean.getPrototypeBean().equals(singleBean2.getPrototypeBean()));
        System.out.println(singleBean.getPrototypeBean().getName());
        System.out.println(singleBean2.getPrototypeBean().getName());
       // System.out.println(singletonBean.getBean().equals(singletonBean.getBean()));
        for (int i=0;i<3;i++){
            singletonBean.print();
        }
        return new ResultUtil<String>().setSuccessMsg("欢迎cloud！");
    }

    @ApiOperation(value = "测试结果集")
    @PostMapping(value = "/result")
    public DefaultResult<String> result(@RequestParam("name") String name){
        log.info("info");
        log.debug("debug");
        log.error("error");
        log.warn("warn");
        throw new BusinessException(DefaultResultEnum.TEST);
    }

    @ApiOperation(value = "测试getUser")
    @PostMapping(value = "/getUser")
    public DefaultResult<CloudUser> getUser(@RequestParam("id") Integer id) {
        CloudUser cloudUser = cloudUserService.getById(id);
        return DefaultResult.success(cloudUser);
    }

    @ApiOperation(value = "测试updateUser")
    @PostMapping(value = "/updateUser")
    public DefaultResult<Boolean> updateUser(@RequestBody CloudUser cloudUser) {
        boolean result = cloudUserService.updateById(cloudUser);
        return DefaultResult.success(result);
    }

    @ApiOperation(value = "测试insertUser")
    @PostMapping(value = "/insertUser")
    public DefaultResult<Integer> insertUser(@RequestBody CloudUser cloudUser) {
        int result = cloudUserService.insertUser(cloudUser);
        return DefaultResult.success(result);
    }


    @ApiOperation(value = "测试redis")
    @PostMapping(value = "/redisSet")
    public DefaultResult<CloudUser> redisSet(@RequestParam("id") String id) {
        User u = new User();
        u.setUsername("hehehehehhehe");
        redisTemplate.opsForValue().set("testCloud", u);
        return DefaultResult.success();
    }

    @ApiOperation(value = "测试redis")
    @PostMapping(value = "/redisGet")
    public DefaultResult<User> redisGet(@RequestParam("id") String id) {
        User result = (User) redisTemplate.opsForValue().get("testCloud");
        return DefaultResult.success(result);
    }

    @ApiOperation(value = "测试异步")
    @PostMapping("/testAsnc")
    public Callable<String> testAsnc() {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(10000);
                log.info("副线程返回");
                return "sucesssssssssssssssssssssssssssssssssss";
            }
        };
        log.info("主线程返回");
        return result;
        //return inventoryRangeService.setCategoryRange(ehpapi.getRequestData());
    }

    @Autowired
    private TestPropertySource testPropertySource;

    @ApiOperation(value = "测试property")
    @PostMapping("/getProperty")
    public DefaultResult<String> property() {
        String lj = testPropertySource.getLj();
        String zzl = testPropertySource.getZzl();
        String ckl = testPropertySource.getCkl();

        log.info(lj+"------------------");
        log.info(zzl+"-------------------");
        log.info(ckl+"---------------------");
        return DefaultResult.success(lj);
        //return inventoryRangeService.setCategoryRange(ehpapi.getRequestData());
    }


}
