package com.tom.shirodemo.easyui.bean;

import java.io.Serializable;
import java.util.List;

public class EasyuiTreeNode implements Serializable {
    private int id ;
    private String text ;
    private String url ;
    private int parentId;
    private  String state ;
    private boolean checked ;
    private String iconCls ;
    private List<EasyuiTreeNode> children ;

    public EasyuiTreeNode() {
    }

    public EasyuiTreeNode(int id, String text, String url, int parentId, String state, boolean checked, String icon, List<EasyuiTreeNode> children) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.parentId = parentId;
        this.state = state;
        this.checked = checked;
        this.iconCls = icon;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String icon) {
        this.iconCls = icon;
    }

    public List<EasyuiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "EasyuiTreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", state='" + state + '\'' +
                ", checked=" + checked +
                ", icon='" + iconCls + '\'' +
                ", children=" + children +
                '}';
    }
}
