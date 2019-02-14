package com.rogchen.ms.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.boot.logging.LogLevel;

/**
 * @Description:
 * <p>
 *  NioEventLoopGroup对应一个被封装好的NIO线程池，bossGroup负责收集客户端连接，
 *  workerGroup负责处理每个连接的IO读写。
 *  ServerBootstrap是Socket服务端启动类。通过这个类的实例，用户可以创建对应的服务端程序。
 * </p>
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/12/27 22:04
 **/
public class NettyTelnetServer {

    private final static int port = 8888;  //端口
    private ServerBootstrap serverBootstrap;

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();


    public void openTelnet() throws InterruptedException {

        serverBootstrap = new ServerBootstrap();
        // 指定socket的一些属性
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)  // 指定是一个NIO连接通道
                .handler(new LoggingHandler(String.valueOf(LogLevel.INFO)))
                .childHandler(new NettyTelnetInitializer());

        // 绑定对应的端口号,并启动开始监听端口上的连接
        Channel ch = serverBootstrap.bind(port).sync().channel();

        // 等待关闭,同步端口
        ch.closeFuture().sync();
    }
    public void close(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
