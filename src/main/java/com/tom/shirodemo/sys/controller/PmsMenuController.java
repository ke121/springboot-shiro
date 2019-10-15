package com.tom.shirodemo.sys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.shirodemo.common.bean.ResultBean;
import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.sys.bean.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("pms/menu")
public class PmsMenuController {
    @Autowired
    MenuService menuService ;

    @RequestMapping("/list")
    @ResponseBody
    public String getPmsMenuList(Integer parentId){
        ObjectMapper mapper = new ObjectMapper() ;
        String json = null ;
        try {
            json = mapper.writeValueAsString( menuService.getMenuByPId(parentId) ) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    return json ;
    }

    @RequestMapping("/addmenu")
    @ResponseBody
    public ResultBean addMenu(MenuVO menuVO){
        return menuService.addMenu(menuVO) ;
    }

    @RequestMapping("/updatemenu")
    @ResponseBody
    public ResultBean updatemenu(MenuVO menuVO){
        return menuService.updatemenu(menuVO) ;
    }
}
