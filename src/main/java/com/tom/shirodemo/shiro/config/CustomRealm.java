package com.tom.shirodemo.shiro.config;

import com.tom.shirodemo.shiro.bean.Permission;
import com.tom.shirodemo.shiro.bean.Role;
import com.tom.shirodemo.shiro.bean.User;
import com.tom.shirodemo.shiro.service.PermissionService;
import com.tom.shirodemo.shiro.service.RoleService;
import com.tom.shirodemo.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService service ;

    @Autowired
    RoleService roleService ;

    @Autowired
    PermissionService permissionService ;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal() ;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo() ;
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
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),this.getSalt(user.getSalt()),getName() );
        return authenticationInfo;
    }

    private ByteSource getSalt(String salt){
       return ByteSource.Util.bytes(salt) ;
    }
}
