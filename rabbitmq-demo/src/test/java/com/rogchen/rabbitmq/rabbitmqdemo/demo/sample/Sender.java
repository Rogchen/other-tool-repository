package com.rogchen.rabbitmq.rabbitmqdemo.demo.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 简单队列，一堆一模式
 *
 * 1---1
 * @product: IntelliJ IDEA
 * @create by 20-4-24 21:48
 **/
public class Sender {

    public static final String QUEUE_NAME = "test_queue_sample";

    public static void main(String[] args) throws IOException, TimeoutException {
//        获取一个连接
        Connection connection = ConnectionUtils.getConnection();
//        获取一个通道
        Channel channel = connection.createChannel();
//        创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        发送的消息内容
        String message = "消息内容发布--------------";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("[sender] message:" + message);
        channel.close();
        connection.close();


    }
}
