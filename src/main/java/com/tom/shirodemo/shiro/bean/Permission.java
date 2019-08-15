package com.tom.shirodemo.shiro.bean;

public class Permission {
    private Integer id;

    private String permissionName;

    private String menuId;

    private String permission;

    private String status;

    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissonName() {
        return permissionName;
    }

    public void setPermissonName(String permissonName) {
        this.permissionName = permissonName == null ? null : permissonName.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public String getPermisson() {
        return permission;
    }

    public void setPermisson(String permisson) {
        this.permission = permisson == null ? null : permisson.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}