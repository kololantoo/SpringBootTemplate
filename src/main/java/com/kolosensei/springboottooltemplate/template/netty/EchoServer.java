package com.kolosensei.springboottooltemplate.template.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/5/6 09:20
 * @description:
 */
public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 1.创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 2.创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    // 3.指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
                    // 4.使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 5.添加一个EchoServerHandler到于Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println(EchoServer.class.getName()+" started and listening for connections on "+future.channel().localAddress());
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}


