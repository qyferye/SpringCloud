package com.cloud.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-16 9:30
 * @ Description：SpringCloud
 */
public class Test {

    public static void main(String[] args) {

       CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "---------------------------");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("sleep---------------------");
                int a =1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).whenCompleteAsync((string, throwable) -> {
           System.out.println(throwable+"======");
            System.out.println(string + "world");
        });

        System.out.println("main---------------------------");





    }
}
