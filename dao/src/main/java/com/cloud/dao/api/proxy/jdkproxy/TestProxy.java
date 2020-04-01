package com.cloud.dao.api.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * @ Description   :  TestProxy
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:44
 */
public class TestProxy {

    public static void main(String[] args) {
        Host host = new Host();//被代理对象
        RentInvocationHandler rentInvocationHandler = new RentInvocationHandler(host);//代理类对象
        /**
         * Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         * ClassLoader loader,真实类对象的类加载器
         * Class<?>[] interfaces,真实类对象的所有的接口
         * InvocationHandler h 代理类对象
         * */
        Rent p = (Rent)Proxy.newProxyInstance(host.getClass().getClassLoader(),host.getClass().getInterfaces(),rentInvocationHandler);
        p.rent();


    }
}
