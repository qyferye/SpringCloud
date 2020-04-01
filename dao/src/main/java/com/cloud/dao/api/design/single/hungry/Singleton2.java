package com.cloud.dao.api.design.single.hungry;

/**
 * @ Description   :  Singleton2 静态代码块
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:06
 */
public class Singleton2 {

    /**
     * 与第一种类似
     * 优点：简单；类装载时实例化，避免线程同步问题
     *
     *缺点：造成内存浪费（如果不是通过getIStance触发的类装载，对象可能从来就没使用过，没有实现lazy loading 效果）
     * */

    //私有化构造函数,禁止外部new
    private Singleton2(){}

    //定义静态变量

    private static  Singleton2 SINGLETON_2 ;

    //实例化
    {
        SINGLETON_2 = new Singleton2();
    }

    //定义获取对象的方法

    public static Singleton2 getInstance(){
        return SINGLETON_2;
    }
}
