package com.tom.shirodemo.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login" ;
    }

    @RequestMapping("/index")
    public String index(){
        return "index" ;
    }

    @RequestMapping("/tologin")
    public String tologin(HttpServletRequest request , String username , String password, Model model){
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        System.out.println(exceptionClassName);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password ) ;
        Subject subject = SecurityUtils.getSubject() ;
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码不正确");
        }catch(UnknownAccountException uae){
            model.addAttribute("msg","账号不存在");
            return "login" ;
        }catch(AuthenticationException ae){
            model.addAttribute("msg","状态不正常");
        }
        if(subject.isAuthenticated()){
            System.out.println("认证成功");
            model.addAttribute("username",username);
            return "index";
        }else{
            token.clear();
            return "login";
        }
     }
}
