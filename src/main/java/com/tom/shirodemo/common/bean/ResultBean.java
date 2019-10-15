package com.tom.shirodemo.common.bean;

import java.io.Serializable;

public class ResultBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code ;
    private String msg ;
    private Object data ;

    public ResultBean() {
    }

    public ResultBean(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(String code) {
        this.code = code;
    }

    public ResultBean success(String msg){
        this.code = "200" ;
        this.msg = msg ;
        return this ;
    }

    public ResultBean fail(String msg){
        this.code = "400" ;
        this.msg = msg ;
        return this ;
    }

    public ResultBean fail(String msg,String code){
        this.code = code ;
        this.msg = msg ;
        return this ;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
