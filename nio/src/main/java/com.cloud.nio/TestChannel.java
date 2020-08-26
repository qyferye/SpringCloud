package com.cloud.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannel {

    @Test
    public void testChannel() {
        try (FileInputStream fis = new FileInputStream("src\\main\\resources\\1.jpg");
             FileOutputStream fos = new FileOutputStream("C:\\Users\\think\\Desktop\\邵利然.jpg");
             FileChannel inChannel = fis.getChannel();
             FileChannel outChannel = fos.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    //直接内存
    @Test
    public void testChannel2() {
        try (FileChannel inChannel = FileChannel.open(Paths.get("src\\main\\resources\\1.jpg"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\think\\Desktop\\邵利然2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        ) {
            //获取内存映射buffer
            MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
          // MappedByteBuffer buffer2 = (MappedByteBuffer) ByteBuffer.allocateDirect(1024);
            MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE,0,inChannel.size());
            byte[] dist = new byte[inBuffer.limit()];
            inBuffer.get(dist);
            outBuffer.put(dist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    //通道之间传输数据   也是直接内存
    @Test
    public void testChannel3() {
        try (FileChannel inChannel = FileChannel.open(Paths.get("src\\main\\resources\\1.jpg"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\think\\Desktop\\2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

        ) {
           inChannel.transferTo(0,inChannel.size(),outChannel);
          // outChannel.transferFrom(inChannel,0,inChannel.size());
            inChannel.close();
            outChannel.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




}
