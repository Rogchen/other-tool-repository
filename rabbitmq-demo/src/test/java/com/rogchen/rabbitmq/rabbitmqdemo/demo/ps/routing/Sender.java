package com.rogchen.rabbitmq.rabbitmqdemo.demo.ps.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 订阅模式队列-路由模式
 * @product: IntelliJ IDEA
 * @create by 20-4-24 21:48
 **/
public class Sender {

    public static final String EXCHANGE_NAME = "test_exchange_name_ps_direct";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        获取一个连接
        Connection connection = ConnectionUtils.getConnection();
//        获取一个通道
        Channel channel = connection.createChannel();
        channel.basicQos(1);    //设置提交1
//        声明交换机-设置模式为路由模式
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = "生产者生产消息了。。。。。。direct";
        String rounting_key = "info";
        channel.basicPublish(EXCHANGE_NAME, rounting_key, null, message.getBytes());
        System.out.println("sender send [1] :" + message);
//        发送的消息内容
        channel.close();
        connection.close();


    }
}
