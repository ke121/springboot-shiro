package com.tom.shirodemo.shiro.service.impl;

import com.tom.shirodemo.shiro.bean.Permission;
import com.tom.shirodemo.shiro.mapper.PermissionMapper;
import com.tom.shirodemo.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper mapper ;
    @Override
    public List<Permission> getPermissionByRoleId(Integer id) {
        return mapper.getPermissionByRoleId(id);
    }
}
