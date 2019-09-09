package com.tom.shirodemo.shiro.mapper;

import com.tom.shirodemo.shiro.bean.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> getMenusByRoleId(Integer roleId) ;

    List<Menu> getMenuByPId(Integer id) ;

    Integer addMenu(Menu menu) ;

    List<Menu> getAllMenu() ;

    Integer updateMenu(Menu menu);
}
