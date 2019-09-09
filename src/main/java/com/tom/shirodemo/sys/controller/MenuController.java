package com.tom.shirodemo.sys.controller;

import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.shiro.service.RoleService;
import com.tom.shirodemo.tree.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService ;

    @Autowired
    RoleService roleService ;

    @Autowired
    TreeService treeService ;

    @RequestMapping("/sys/menu")
    public String getMenus() throws Exception{
        return "sysmenu" ;
    }

}
