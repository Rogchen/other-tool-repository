package com.rogchen.iodemo.iodemo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 19-3-21 22:26
 **/
public class NioStudy {


    public static void main(String[] args) throws IOException {
        //客户端推出导致服务端推出
        niodemo();
    }

    public static void niodemo() throws IOException {
        //channel多复用技术
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        socketChannel.configureBlocking(false);  //兼容bio
        socketChannel.bind(new InetSocketAddress(9093));
        System.out.println("NIO server has start,Listening on port: " + socketChannel.getLocalAddress());
        //Selector选择器
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //第一次注册一个新的channel到选择器并修改channel状态，第二次进入
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = channel.accept();
                    System.out.println("connection from :" + clientChannel.getRemoteAddress());
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                //第二次才被选择进行数据读写（多了一次循环）
                if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    channel.read(buffer);
                    String msg = new String(buffer.array()).trim();
                    buffer.clear();
                    System.out.println(String.format("From %s : %s ",channel.getRemoteAddress(),msg));
                    msg = "response msg to:"+new String(buffer.array()).trim()+"\r\n";
                    channel.write(ByteBuffer.wrap(msg.getBytes()));
                }
                iterator.remove();
            }
        }


    }
}
