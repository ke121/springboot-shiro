package com.tom.shirodemo.shiro.service.impl;

import com.tom.shirodemo.common.ResultBean;
import com.tom.shirodemo.shiro.bean.Menu;
import com.tom.shirodemo.shiro.bean.User;
import com.tom.shirodemo.shiro.mapper.MenuMapper;
import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.sys.bean.MenuVO;
import com.tom.shirodemo.tree.service.TreeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    MenuMapper mapper ;
    @Override
    public List<Menu> getMenusByRoleId(Integer roleId) {
        return mapper.getMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> getMenuByPId(Integer id) {
        return mapper.getMenuByPId(id);
    }

    @Override
    public List<Menu> getAllMenu() {
        return mapper.getAllMenu();
    }

    @Autowired
    TreeService treeService ;

    @Override
    public ResultBean addMenu(MenuVO menuVO) {
        ResultBean resultBean = new ResultBean() ;
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute("user");
        String text = menuVO.getMenuName() ;
        String  remarker = menuVO.getMenuRemarker() ;
        String parentId = menuVO.getParentId() ;
        String url = menuVO.getRequestUrl() ;
        Menu menu = new Menu() ;
        menu.setText(text);
        menu.setUrl(url);
        menu.setParentId(Integer.valueOf(parentId));
        menu.setRemarker(remarker);
        menu.setCreateTime(new Date());
        menu.setDescription("1");
        menu.setState("closed");
        menu.setCreator(user.getUsername());
        if((mapper.addMenu(menu))>0){
            resultBean.success("操作成功") ;
            treeService.refreshSysTree();
        }else {
            resultBean.fail("操作失败") ;
        }
        return resultBean;
    }

    @Override
    public ResultBean updatemenu(MenuVO menuVO) {
        ResultBean resultBean = new ResultBean() ;
        String text = menuVO.getMenuName() ;
        String  remarker = menuVO.getMenuRemarker() ;
        String parentId = menuVO.getParentId() ;
        String url = menuVO.getRequestUrl() ;
        String id = menuVO.getId() ;
        Menu menu = new Menu() ;
        menu.setId(Integer.valueOf(id));
        menu.setText(text);
        menu.setUrl(url);
        menu.setRemarker(remarker);
        if((mapper.updateMenu(menu))>0){
            resultBean.success("操作成功") ;
            treeService.refreshSysTree();
        }else {
            resultBean.fail("操作失败") ;
        }
        return resultBean;
    }
}
