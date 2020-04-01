package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy2 同步方法
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:19
 */
public class Lazy2 {


    //私有化构造器
    private Lazy2(){}

    //静态属性
    private static Lazy2 instance;

    //获取实例
    public static synchronized Lazy2 getInstance(){
        if(instance == null){
            instance = new Lazy2();
        }
        return instance;
    }
/**
 * 优点：按需加载，线程安全
 *
 * 缺点：效率低下
 *
 *
 * */
}
