package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy3 同步代码块
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:21
 */
public class Lazy3 {


    //私有化构造器
    private Lazy3(){}

    //静态属性
    private static Lazy3 instance;

    //获取实例
    public static  Lazy3 getInstance(){
        if(instance == null){
            synchronized(Lazy3.class){
                instance = new Lazy3();
            }

        }
        return instance;
    }
/**
 * 优点：按需加载
 *
 * 缺点：线程不安全（与第一种一样，效率更低，还不安全，还不如第一种）
 * */
}
