package com.tom.shirodemo.shiro.service.impl;

import com.tom.shirodemo.shiro.bean.Role;
import com.tom.shirodemo.shiro.mapper.RoleMapper;
import com.tom.shirodemo.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper mapper ;
    @Override
    public List<Role> getRolesByUserName(String username) {
        return mapper.getRolesByUserName(username);
    }
}
