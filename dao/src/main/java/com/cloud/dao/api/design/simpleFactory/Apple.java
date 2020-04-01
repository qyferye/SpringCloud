package com.cloud.dao.api.design.simpleFactory;

/**
 * @ Description   :  Apple
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-31 9:16
 */
public class Apple extends Fruit{
    @Override
    public void show() {
        System.out.println(name);
    }
}
