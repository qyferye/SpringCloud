package com.cloud.dao.api.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ Description   :  RentInvocationHandler
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:37
 */
public class RentInvocationHandler implements InvocationHandler {

    private Object object;

    public RentInvocationHandler(Object object) {
        this.object = object;
    }

    /**
     * @param proxy:代理类
     * @param method：代理类调用处理程序的方法对象
     * @param args：方法的参数
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用jdk动态代理在代理方法--前--增强");
        Object result = method.invoke(object,args);
        System.out.println("使用jdk动态代理在代理方法--后--增强");
        return result;
    }
}
