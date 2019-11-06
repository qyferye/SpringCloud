package com.cloud.api.controller;

import com.cloud.core.common.util.ResultUtil;
import com.cloud.core.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cloud")
@Api( "测试token接口")
public class SecurityController {
    @ApiOperation(value = "测试token接口")
    @PostMapping(value = "/needLogin")
    public Result<Object> noLogin(){
        return new ResultUtil<Object>().setSuccessMsg("欢迎cloud！");
    }

}