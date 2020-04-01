package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy1
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:11
 */
public class Lazy1 {

    //私有化构造器
    private Lazy1(){}

    //静态属性
    private static Lazy1 instance;

    //获取实例
    public static Lazy1 getInstance(){
        if(instance == null){
            instance = new Lazy1();
        }
        return instance;
    }
/**
 * 优点：按需加载
 *
 * 缺点：多线程不安全，可能创建多个实例
 *
 *
 * */

}
