package com.cloud.dao.api.proxy.cglibproxy;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class RentMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("使用CGLib方式做的增强......");
        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}