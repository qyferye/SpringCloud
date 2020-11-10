package com.cloud.thread.join;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 14:19
 * @ Description：SpringCloud
 */

public class MessageMonitor {
    private Object object ;

    public Object getObject() {
        return object;
    }
    private void setObject(Object o){
        this.object=o;
    }

    //获取消息
    public synchronized void produceMessage(){
        System.out.println("产生消息......");
        setObject(new Object());
        ConcurrentHashMap<String, String> ma = new ConcurrentHashMap<>();
        ma.computeIfAbsent("",(key)->{return "";});
        notifyAll();
    }

    //产生消息
    public synchronized Object getMessage(){
        while(this.object ==null){
            try {
                System.out.println("等待消息中.....");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.object;
    }

    public static void main(String[] args) {
        MessageMonitor monitor = new MessageMonitor();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                monitor.produceMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            monitor.getMessage();
        }).start();
    }

}
