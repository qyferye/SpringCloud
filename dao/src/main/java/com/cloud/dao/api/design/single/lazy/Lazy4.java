package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy4  双重检查
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:26
 */
public class Lazy4 {


    //私有化构造器
    private Lazy4() {
    }

    //静态属性  可见性
    private static volatile Lazy4 instance;

    //获取实例
    public static Lazy4 getInstance() {
        if (instance == null) {
            synchronized (Lazy4.class) {
                if (instance == null) {
                    instance = new Lazy4();
                }
            }

        }
        return instance;
    }
/**
 * 优点：按需加载,线程安全
 *
 * 缺点：如果第一次实例化时，多线程，效率可能会低
 *
 * 推荐使用
 * */


}
