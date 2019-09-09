package com.tom.shirodemo.shiro.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.shirodemo.easyui.bean.EasyuiTreeNode;
import com.tom.shirodemo.shiro.bean.*;
import com.tom.shirodemo.shiro.service.MenuService;
import com.tom.shirodemo.shiro.service.PermissionService;
import com.tom.shirodemo.shiro.service.RoleService;
import com.tom.shirodemo.shiro.service.UserService;
import com.tom.shirodemo.tree.service.TreeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService service ;

    @Autowired
    RoleService roleService ;

    @Autowired
    PermissionService permissionService ;

    @Autowired
    MenuService menuService ;

    @Autowired
    TreeService treeService ;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal() ;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo() ;
//        Subject subject = SecurityUtils.getSubject() ;
//        Session session = subject.getSession() ;
//        User sessionuser = (User) session.getAttribute("user") ;
//        if(sessionuser == null){
//            session.setAttribute("user",user );
//        }
        String username = user.getUsername() ;
        List<Role> roles = roleService.getRolesByUserName(username) ;
        for(Role role:roles){
            simpleAuthorizationInfo.addRole(role.getRoleName()) ;
            for(Permission permission :  permissionService.getPermissionByRoleId(role.getId() )){
                simpleAuthorizationInfo.addStringPermission(permission.getPermisson());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = (String) authenticationToken.getPrincipal();
        User user = service.getUserByName(loginName);
        if(user == null){
            throw new UnknownAccountException() ;
        }
        if("I".equals(user.getStatus())){
            throw new LockedAccountException() ;
        }
        Session session =  SecurityUtils.getSubject().getSession() ;
        SecurityUtils.getSubject().getSession().setAttribute("user",user );
        //把tree加载到session中,同时把菜单管理的tree也加入进来
        List<Role> roles1 = roleService.getRolesByUserName(user.getUsername()) ;
        List<Menu> menuslist = new ArrayList<>() ;
        for (Role role:roles1){
            List<Menu> menus =  menuService.getMenusByRoleId(role.getId()) ;
            menuslist.addAll(menus) ;
        }
        List<EasyuiTreeNode> easyuiTreeNodes = treeService.getTreeNodesByMenus(menuslist) ;
        List<EasyuiTreeNode> easyuiSysTree = treeService.getTreeNodesByMenus(menuService.getAllMenu()) ;
        ObjectMapper mapper = new ObjectMapper() ;
        String userTree = null ;
        String sysTree = null ;
        try {
            userTree = mapper.writeValueAsString(easyuiTreeNodes) ;
            sysTree = mapper.writeValueAsString(easyuiSysTree) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        session.setAttribute("tree",userTree );
        session.setAttribute("systree", sysTree);
        System.out.println("-------------" + easyuiTreeNodes);
        System.out.println("=================" + sysTree);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),this.getSalt(user.getSalt()),getName() );
        return authenticationInfo;
    }

    private ByteSource getSalt(String salt){
       return ByteSource.Util.bytes(salt) ;
    }
}
