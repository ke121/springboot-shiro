package com.tom.shirodemo.shiro.service;

import com.tom.shirodemo.shiro.bean.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> getPermissionByRoleId(Integer id) ;
}
