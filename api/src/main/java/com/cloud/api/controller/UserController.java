package com.cloud.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ignore/user")
public class UserController {

    @RequestMapping("/get")
    public String add(){
        return "product/add";
    }
}
