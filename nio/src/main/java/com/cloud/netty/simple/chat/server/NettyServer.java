package com.cloud.netty.simple.chat.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        //创建两个事件组  相当于nio select 池 中之物
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);//默认系统内核数*2
        EventLoopGroup workGroup = new NioEventLoopGroup();


        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                //.handler(null) //对应bossGroup
                .childHandler(new ServerInitializer());

        try {
            System.out.println("服务器启动。。。");
            ChannelFuture future = bootstrap.bind(6666).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
