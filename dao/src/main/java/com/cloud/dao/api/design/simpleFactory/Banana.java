package com.cloud.dao.api.design.simpleFactory;

/**
 * @ Description   :  Banana
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 17:09
 */
public class Banana extends Fruit {
    @Override
    public void show() {
        System.out.println(name);
    }
}
