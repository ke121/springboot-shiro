package com.tom.shirodemo.common.enums;


public enum ReturnCode {

    SUCCESS("00","SUCCESS"),
    FAIL("01","ERROR"),
    PARAMS_ERROR("02","参数不合法"),
    SIGN_ERROR("03","验签失败"),
    SYSTEM_ERROR("99","系统异常"),

    ;

    private final String code;
    private final String desc;


    ReturnCode(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
