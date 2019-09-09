package com.tom.shirodemo.shiro.bean;

import java.util.Date;

public class Menu {
    private int id ;
    private String text ;
    private String url ;
    private int parentId;
    private Date createTime ;
    private  String state ;
    private boolean checked ;
    private String Description ;
    private String icon ;
    private String creator ;
    private String remarker ;

    public Menu() {
    }

    public Menu(int id, String text, String url, int parentId, Date createTime, String state, boolean checked, String description, String icon, String creator, String remarker) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.parentId = parentId;
        this.createTime = createTime;
        this.state = state;
        this.checked = checked;
        Description = description;
        this.icon = icon;
        this.creator = creator;
        this.remarker = remarker;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRemarker() {
        return remarker;
    }

    public void setRemarker(String remarker) {
        this.remarker = remarker;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                ", state='" + state + '\'' +
                ", checked=" + checked +
                ", Description='" + Description + '\'' +
                ", icon='" + icon + '\'' +
                ", creator='" + creator + '\'' +
                ", remarker='" + remarker + '\'' +
                '}';
    }
}
