package com.tom.shirodemo.tree.service;

import com.tom.shirodemo.easyui.bean.EasyuiTreeNode;
import com.tom.shirodemo.shiro.bean.Menu;

import java.util.List;

public interface TreeService {
    //根据菜单列表获取easyui的树
     List<EasyuiTreeNode> getTreeNodesByMenus(List<Menu> menus) ;

    //根据parentid获取childNode
    List<EasyuiTreeNode> getChildNodesByPid(int parentId,List<Menu> menus) ;

    //刷新systree
    void refreshSysTree() ;
}
