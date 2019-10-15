package com.tom.shirodemo.common.Exception;

import com.tom.shirodemo.common.enums.ReturnCode;
import lombok.Getter;

//业务异常
@Getter
public class BizException extends RuntimeException {
    private String code ;

    public BizException(ReturnCode code){
        super(code.getDesc());
        this.code = code.getCode() ;
    }

    public BizException(String msg,String code){
        super(msg);
        this.code = code ;
    }
}
