package com.rogchen.rabbitmq.rabbitmqdemo.demo.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 工作队列
 * @product: IntelliJ IDEA
 * @create by 20-4-24 21:48
 **/
public class Sender {

    public static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        获取一个连接
        Connection connection = ConnectionUtils.getConnection();
//        获取一个通道
        Channel channel = connection.createChannel();
//        创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 65; i++) {
            String message = "消息内容发布--------------" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[sender] message:" + message);
            TimeUnit.MILLISECONDS.sleep(100);
        }
//        发送的消息内容
        channel.close();
        connection.close();


    }
}
