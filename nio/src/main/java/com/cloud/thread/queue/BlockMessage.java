package com.cloud.thread.queue;

/**
 * @ Author     ：cloud
 * @ Date       ：Created in  2020-09-09 14:49
 * @ Description：SpringCloud
 */
public class BlockMessage {
    private int id;
    private String content;

    public BlockMessage(int id,String content){
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlockMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
