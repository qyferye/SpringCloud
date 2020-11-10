package com.cloud.thread.volatileT;

import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-10 10:17
 * @ Description：SpringCloud
 */
public class volatileT {
    static  boolean flag =true;


    public static void main(String[] args) throws InterruptedException {
         Object o = new Object();
        new Thread(()->{
            while (flag){
                /*System.out.println("-----------");*/
                synchronized (o){
                    int i = 1;
                }

            }
        },"threadT").start();
        TimeUnit.SECONDS.sleep(1);
        flag=false;



    }
}
