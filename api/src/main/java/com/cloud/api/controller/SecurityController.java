package com.cloud.api.controller;

import com.cloud.core.common.util.ResultUtil;
import com.cloud.core.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
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

    @Secured({"ROLE_USER"})//springSecurity 注解
    //@RolesAllowed({"ROLE_USER"})//jsr250注解
    //@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")//spring注解

    public Result<Object> noLogin(){
        return new ResultUtil<Object>().setSuccessMsg("欢迎 hello cloud!let's go baby");
    }

}