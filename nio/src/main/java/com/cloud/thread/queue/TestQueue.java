package com.cloud.thread.queue;

import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 15:00
 * @ Description：SpringCloud
 */
public class TestQueue {

    public static void main(String[] args) {
        BlockQueue queue = new BlockQueue(3);
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            queue.getMessage();
            }
        },"消息消费者").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            int id = i;
            new Thread(()->{
                queue.produceMessage(new BlockMessage(id,"消息内容"+id));
            },"消息生产者"+i).start();
        }


    }
}
