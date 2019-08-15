package com.tom.shirodemo.shiro.mapper;

import com.tom.shirodemo.shiro.bean.Permission;

import java.util.List;


public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionByRoleId(Integer id);
}