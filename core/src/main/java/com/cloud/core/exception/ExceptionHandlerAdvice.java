package com.cloud.core.exception;

import com.cloud.core.common.constant.DefaultResultEnum;
import com.cloud.core.dto.DefaultResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
 
    @ExceptionHandler(Exception.class)
    public DefaultResult<Object> handleException(Exception e) {
        /*当时  1/0 时 异常 e  的各种信息*/
        System.out.println("getMessage  "+e.getMessage()); //getMessage  / by zero
       // System.out.println("getCause  "+ e.getCause());// getCause  null
       // System.out.println("getStackTrace  "+e.getStackTrace());//  getStackTrace  [Ljava.lang.StackTraceElement;@1c33f773
        System.out.println("e.toString  "+e.toString());//  e.toStringjava.lang.ArithmeticException: / by zero
        return DefaultResult.fail(DefaultResultEnum.TEST);
    }

    @ExceptionHandler(BusinessException.class)
    public DefaultResult<Object> businessException(BusinessException e) {
        return DefaultResult.fail(e.getCode(),e.getMsg());
    }
 
}