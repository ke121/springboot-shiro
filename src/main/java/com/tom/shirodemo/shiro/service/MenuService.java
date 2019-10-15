package com.tom.shirodemo.shiro.service;

import com.tom.shirodemo.common.bean.ResultBean;
import com.tom.shirodemo.shiro.bean.Menu;
import com.tom.shirodemo.sys.bean.MenuVO;

import java.util.List;

public interface MenuService {
    List<Menu> getMenusByRoleId(Integer roleId) ;

    List<Menu> getMenuByPId(Integer id) ;

    List<Menu> getAllMenu() ;

    ResultBean addMenu(MenuVO menu) ;

    ResultBean updatemenu(MenuVO menuVO);
}
