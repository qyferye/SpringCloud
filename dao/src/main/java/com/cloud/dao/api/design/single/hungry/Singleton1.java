package com.cloud.dao.api.design.single.hungry;

/**
 * @ Description   :  Singleton1  饿汉式 静态变量
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 13:56
 */
public class Singleton1 {

    /**
     * 优点：简单；类装载时实例化，避免线程同步问题
     *
     *缺点：造成内存浪费（如果不是通过getIStance触发的类装载，对象可能从来就没使用过，没有实现lazy loading 效果）
     * */

    //私有化构造函数,禁止外部new
    private Singleton1(){}

    //定义静态变量

    private static final Singleton1 SINGLETON_1 = new Singleton1();

    //定义获取对象的方法

    public static Singleton1 getInstance(){
        return SINGLETON_1;
    }

}
