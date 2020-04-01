package com.cloud.dao.api.design.simpleFactory;

/**
 * @ Description   :  FruitFactory
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-04-01 17:09
 */
public class FruitFactory {

    public static Fruit getFruit(String type){
        Fruit fruit = null;
        if("apple".equals(type)){
            System.out.println("apple");
            fruit = new Apple();
        } else if("banana".equals(type)){
            System.out.println("banana");
            fruit = new Banana();
        }else {
            System.out.println("no");
        }
            return fruit;

    }
}
