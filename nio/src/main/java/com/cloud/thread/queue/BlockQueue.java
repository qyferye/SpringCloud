package com.cloud.thread.queue;

import java.util.LinkedList;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 14:47
 * @ Description：先进先出  线程安全
 */


public class BlockQueue {
    private LinkedList list = new LinkedList<BlockMessage>();
    private int capacity;

    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }

    //生产消息
   public void produceMessage(BlockMessage message){
       synchronized (list){
           while (capacity<list.size()){
               try {
                   System.out.println("队列已满");
                   list.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           list.addLast(message);
           System.out.println("产生消息:"+message);
           list.notifyAll();
       }
   }

    //消费消息
    public BlockMessage getMessage(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    System.out.println("消息队列为空！等待消息产生");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            BlockMessage blockMessage = (BlockMessage) list.removeFirst();
            list.notifyAll();
            System.out.println("消费消息:"+blockMessage);
            return blockMessage;
        }
    }
}
