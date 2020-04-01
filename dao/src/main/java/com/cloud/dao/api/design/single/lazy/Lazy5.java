package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy5  静态内部类
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:35
 */
public class Lazy5 {

    //私有化构造器
    private Lazy5(){}

    /*类装载时，静态内部类不会装载*/
    /*类装载时线程安全*/
    private static class Lazy5Instance{
        private static final Lazy5 INSTANCE = new Lazy5();
    }

    public static Lazy5 getInstance(){
        return Lazy5Instance.INSTANCE;
    }
/**
* 优点：懒加载，线程安全，效率高
 *
 * 推荐
* */

}
