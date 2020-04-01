package com.cloud.dao.api.proxy.jdkproxy;

/**
 * 真实角色：被代理的角色
 * @ Description   :  Host  房东  真实角色，实现抽象角色对应的接口（Rent）
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-25 14:03
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("我要出租房子");
    }
}
