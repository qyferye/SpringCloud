package com.cloud.dao.api.proxy.cglibproxy;

/**
 * 真实角色：被代理的角色
 * @ Description   :  Host  房东  真实角色
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:03
 */
public class Host{

    public void rent() {
        System.out.println("我要出租房子");
    }
    public void show() {
        System.out.println("我要演出面试");
    }
}
