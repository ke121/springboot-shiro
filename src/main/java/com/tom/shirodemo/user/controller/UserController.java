package com.tom.shirodemo.user.controller;

import com.tom.shirodemo.shiro.bean.Permission;
import com.tom.shirodemo.shiro.bean.Role;
import com.tom.shirodemo.shiro.service.PermissionService;
import com.tom.shirodemo.shiro.service.RoleService;
import com.tom.shirodemo.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserService userService ;
    @RequestMapping("/user/{username}")
    public String User(@PathVariable String username){
        return userService.getUserByName(username).toString() ;
    }

    @Autowired
    RoleService roleService ;
    @RequestMapping("/user/roles")
    public List<Role> roles(String username){
        return roleService.getRolesByUserName(username) ;
    }

    @Autowired
    PermissionService permissionService ;
    @RequestMapping("/user/permissions")
    public List<Permission> permissions(Integer id){
        return permissionService.getPermissionByRoleId(id) ;
    }

}

