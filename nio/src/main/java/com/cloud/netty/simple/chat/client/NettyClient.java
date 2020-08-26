package com.cloud.netty.simple.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NettyClient {


    public static void main(String[] args) {

            NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

            try {
                //用于启动服务端的类
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                        .handler(new ClientInitializer());
                ChannelFuture channelFuture = bootstrap.connect("localhost", 6666).sync();
                Channel channel = channelFuture.channel();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (true){
                    channel.writeAndFlush(br.readLine() + "\r\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                eventLoopGroup.shutdownGracefully();
            }

        }
}
