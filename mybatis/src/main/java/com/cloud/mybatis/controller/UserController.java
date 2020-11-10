package com.cloud.mybatis.controller;

import com.cloud.mybatis.entity.UserDomain;
import com.cloud.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(@RequestBody UserDomain user){
        System.out.println("-----------------");
        return userService.addUser(user);
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize, HttpServletRequest request){
        log.info("日志");
        System.out.println("ip------------"+request.getRemoteAddr());
        return userService.findAllUser(pageNum,pageSize);
    }
}