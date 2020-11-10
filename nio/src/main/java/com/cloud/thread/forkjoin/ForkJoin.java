package com.cloud.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-14 11:26
 * @ Description：SpringCloud
 */
public class ForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask task = new MyTask(5);
        Integer invoke = forkJoinPool.invoke(task);
        System.out.println(invoke);
    }


}

class MyTask extends RecursiveTask<Integer>{
    private Integer n;

    public MyTask(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n==1) {
            return 1;
        }
        MyTask task = new MyTask(n-1);
        task.fork();
        return n+task.join();
    }
}
