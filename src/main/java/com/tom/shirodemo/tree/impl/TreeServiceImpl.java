package com.tom.shirodemo.tree.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.shirodemo.easyui.bean.EasyuiTreeNode;
import com.tom.shirodemo.shiro.bean.Menu;
import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.tree.service.TreeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

    @Override
    public List<EasyuiTreeNode> getTreeNodesByMenus(List<Menu> menus) {
        List<EasyuiTreeNode> treeNodes = new ArrayList<>();

        //祖先菜单列表
        List<Menu> menuList = new ArrayList<>();
        if (menus != null) {
            for (Menu menu : menus) {
                if (menu.getParentId() == 0) {
                    menuList.add(menu);
                }
            }
            //如果没有祖先菜单直接返回null
            if (menuList.size() < 1) {
                return null;
            }

            for (Menu menu : menuList) {
                EasyuiTreeNode treeNode = new EasyuiTreeNode();
                treeNode.setId(menu.getId());
                treeNode.setIconCls(menu.getIcon());
                treeNode.setText(menu.getText());
                treeNode.setUrl(menu.getUrl());
                treeNode.setChildren(getChildNodesByPid(menu.getId(), menus));
                treeNode.setState(menu.getState());
                treeNodes.add(treeNode) ;
            }
        }
        return treeNodes;
    }

    @Override
    public List<EasyuiTreeNode> getChildNodesByPid(int parentId, List<Menu> menus) {
        //孩子菜单列表
        List<Menu> menuList = new ArrayList<>();
        List<EasyuiTreeNode> easyuiTreeNodeList = new ArrayList<>() ;
        List<EasyuiTreeNode> easyuiTreeNodes = new ArrayList<>() ;
            for (Menu menu : menus) {
                if (menu.getParentId() == parentId) {
                    menuList.add(menu);
                }
            }
            //如果没有祖先菜单直接返回null
            if (menuList.size() < 1) {
                return null;
            }
            for (Menu menu1: menuList){
                EasyuiTreeNode treeNode = new EasyuiTreeNode();
                treeNode.setId(menu1.getId());
                treeNode.setIconCls(menu1.getIcon());
                treeNode.setText(menu1.getText());
                treeNode.setUrl(menu1.getUrl());
                treeNode.setChildren(getChildNodesByPid(menu1.getId(),menus ));
                easyuiTreeNodeList.add(treeNode) ;
            }
        return easyuiTreeNodeList ;
    }

    @Autowired
    MenuService menuService ;

    @Override
    public void refreshSysTree() {
        Session session =  SecurityUtils.getSubject().getSession() ;
        List<EasyuiTreeNode> easyuiTreeNodes = getTreeNodesByMenus(menuService.getAllMenu()) ;
        ObjectMapper mapper = new ObjectMapper() ;
        String sysTree = null ;
        try {
            sysTree = mapper.writeValueAsString(easyuiTreeNodes) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String json = (String)session.getAttribute("systree") ;
        System.out.println(json);
        session.setAttribute("systree", sysTree);
    }
}
