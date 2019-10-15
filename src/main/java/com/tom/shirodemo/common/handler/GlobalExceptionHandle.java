package com.tom.shirodemo.common.handler;

import com.tom.shirodemo.common.Exception.BizException;
import com.tom.shirodemo.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String defaultExceptionHandler(AuthorizationException e){
        return "您没有访问权限" + e.getMessage() ;
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultBean handlerBizException(BizException e){
        return new ResultBean().fail(e.getMessage(),e.getCode()) ;
    }


}
