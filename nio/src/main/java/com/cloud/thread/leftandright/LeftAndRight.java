package com.cloud.thread.leftandright;

import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 15:45
 * @ Description：SpringCloud
 */
public class LeftAndRight {

    private final static Object Object = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                synchronized (Object){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("左脚");
                    Object.notify();
                    try {
                        Object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"左脚-----").start();
        new Thread(()->{
            while (true){
                synchronized (Object){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("右脚");
                    Object.notify();
                    try {
                        Object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"右脚-----").start();


    }

}
