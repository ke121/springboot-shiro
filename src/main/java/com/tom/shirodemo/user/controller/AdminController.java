package com.tom.shirodemo.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AdminController {
    @RequiresPermissions("admin:view")
    @RequestMapping("/admin")
    public String admin(){
        return "admin" ;
    }

    @RequestMapping("/hello")
    public String hello(){
        log.info("ttttttttttttttttt");
        return "hello" ;
    }

    @RequestMapping("/getIp")
    public String test() throws Exception{
        return "ip:"+java.net.InetAddress.getLocalHost().getHostAddress() ;
    }
}
