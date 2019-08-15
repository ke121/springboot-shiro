package com.tom.shirodemo.user.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @RequiresPermissions("admin:view")
    @RequestMapping("/admin")
    public String admin(){
        return "admin" ;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello" ;
    }
}
