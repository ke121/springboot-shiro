package com.tom.shirodemo.shiro.service;

import com.tom.shirodemo.shiro.bean.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRolesByUserName(String username) ;
}
