package com.cloud.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class TestBuffer {

/*
* java 中每种基本数据类型都对应一个 buffer  ， IntBuffer ByteBuffer  等 除了bool
*
* buffer中 四个基本属性  mark  position  limit  capacity
* mark <= position <= limit <= capacity
* */
    @Test
    public void test1(){
        // 应用程序——》jvm（用户态）——》os（内核态）——》磁盘
        //ByteBuffer buffer = ByteBuffer.allocateDirect(1024);///直接缓冲区  操作系统内存  效率高//失控、内存消耗大
        ByteBuffer buffer = ByteBuffer.allocate(1024);///非直接缓冲区 jvm内存
        System.out.println(buffer.isDirect());buffer.get();
        String data = "abcde";
        byte[] datas = data.getBytes();

        System.out.println("/******************测试初始属性*****************/");
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());


        System.out.println("/******************测试写入数据put*****************/");
        buffer.put(datas);
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());


        System.out.println("/******************测试读取数据get*****************/");
        //先flip 切换读写模式
        buffer.flip();
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());
        byte[] dist = new byte[4];
        buffer.get(dist);

        System.out.println("---读取到的数据--"+new String(dist));
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());

        System.out.println(" /******************测试mark*****************/");
        buffer.mark();
        byte[] dist2 = new byte[1];
        buffer.get(dist2);
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());
        System.out.println(" /********reset后************/");
        buffer.reset();
        System.out.println("position:"+buffer.position());
        System.out.println("limit:"+buffer.limit());
        System.out.println("capacity:"+buffer.capacity());



        System.out.println(" /******************测试clear*****************/");
    }

/**
 *

 对缓冲区的读写操作首先要知道缓冲区的下限、上限和当前位置。下面这些变量的值对Buffer类中的某些操作有着至关重要的作用：

 limit：所有对Buffer读写操作都会以limit变量的值作为上限。
 position：代表对缓冲区进行读写时，当前游标的位置。
 capacity：代表缓冲区的最大容量（一般新建一个缓冲区的时候，limit的值和capacity的值默认是相等的）。

 flip、rewind、clear这三个方法便是用来设置这些值的。
 clear方法
 public final Buffer clear()
 {
 position = 0; //重置当前读写位置
 limit = capacity;
 mark = -1;  //取消标记
 return this;
 }


 clear方法将缓冲区清空，一般是在重新写缓冲区时调用。
 flip方法
 public final Buffer flip() {
 limit = position;
 position = 0;
 mark = -1;
 return this;
 }

 反转缓冲区。首先将限制设置为当前位置，然后将位置设置为 0。如果已定义了标记，则丢弃该标记。 常与compact方法一起使用。通常情况下，在准备从缓冲区中读取数据时调用flip方法。
 rewind方法


 public final Buffer rewind() {
  position = 0;
  mark = -1;
  return this;
 }
 以上三种方法均使用final修饰，java.nio.Buffer的所有子类均使用同一种flip、clear和rewind机制。

 *
 * */
}
