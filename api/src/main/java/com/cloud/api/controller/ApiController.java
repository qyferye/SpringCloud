package com.cloud.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiController {
    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/errorPage")
    public String error(){
        return "/errorPage";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "/login";
    }
}
