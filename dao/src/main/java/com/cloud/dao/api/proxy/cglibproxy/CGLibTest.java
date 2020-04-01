package com.cloud.dao.api.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

public class CGLibTest {
    public static void main(String[] args) {
        //设置增强需要用到的类
        Enhancer enhancer = new Enhancer();
        //设置需要增强的类（这里是Host类）
        enhancer.setSuperclass(Host.class);
        //设置需要回调的拦截器
        enhancer.setCallback(new RentMethodInterceptor());
        //生成对应的增强类
        Host host = (Host)enhancer.create();
        host.rent();
        host.show();
    }
}