package com.rogchen.rabbitmq.rabbitmqdemo.demo.product.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 普通模式，单条模式
 * @product: IntelliJ IDEA
 * @create by 20-4-25 00:47
 **/
public class ConfirmSender1 {

    public static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

//        跟tx是互斥的
        channel.confirmSelect();//开启confirm
        String message = "生产者生产消息了。。。。。。confirm";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        if (channel.waitForConfirms()) {
            System.out.println("send success");
        } else {
            System.out.println("send failed");
        }
        channel.close();
        connection.close();
    }
}
