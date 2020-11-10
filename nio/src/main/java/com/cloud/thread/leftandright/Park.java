package com.cloud.thread.leftandright;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 16:40
 * @ Description：SpringCloud
 */
public class Park {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            System.out.println("start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park");
            LockSupport.park();
            LockSupport.park();
            System.out.println("going...");

        },"park");
        t.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("un park");
        LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("un park2 2");
        //t.interrupt();
        LockSupport.unpark(t);

    }
}
