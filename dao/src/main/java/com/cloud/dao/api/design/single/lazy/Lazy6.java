package com.cloud.dao.api.design.single.lazy;

/**
 * @ Description   :  Lazy6 枚举
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 14:54
 */
public enum  Lazy6 {

    INSTANCE;

    public void say(){
        System.out.println("tmd zz");
    }

    /**
     * 防止反序列化重新创建对象
     * Effective Java 作者  Josh Bloch  推荐
     *
     * 推荐
     * */

}
