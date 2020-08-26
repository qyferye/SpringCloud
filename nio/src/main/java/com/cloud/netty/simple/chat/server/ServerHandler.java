package com.cloud.netty.simple.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext context, String s) throws Exception {
        try {
            Channel channel = context.channel();
            channels.forEach(channel1 -> {
                if(channel != channel1){
                    System.out.println("服务器收到消息：进行处理的线程"+Thread.currentThread().getName());
                    channel1.writeAndFlush("小子："+s+"\n");
                }
            });
        }catch (Exception e){
            System.out.println("客户已断开：");
        }


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("[服务器：]"+channel.remoteAddress()+"加入连接,当前处理线程"+Thread.currentThread().getName());
        channels.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"上线");
        System.out.println("当前在线人数"+channels.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"下线");
        System.out.println("当前在线人数"+channels.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.remove(channel);
        channels.writeAndFlush("[服务器：]"+channel.remoteAddress()+"断开连接");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel();
    }


}
