package com.tom.shirodemo.sys.bean;

public class MenuVO {
    private String parentMenu ;
    private String menuRemarker ;
    private String requestUrl ;
    private String menuName ;
    private String parentId ;
    private String id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuVO() {
    }

    public MenuVO(String parentMenu, String menuRemarker, String requestUrl, String menuName, String parentId) {
        this.parentMenu = parentMenu;
        this.menuRemarker = menuRemarker;
        this.requestUrl = requestUrl;
        this.menuName = menuName;
        this.parentId = parentId;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getMenuRemarker() {
        return menuRemarker;
    }

    public void setMenuRemarker(String menuRemarker) {
        this.menuRemarker = menuRemarker;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
