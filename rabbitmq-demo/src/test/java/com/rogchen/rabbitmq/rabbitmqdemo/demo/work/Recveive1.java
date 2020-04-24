package com.rogchen.rabbitmq.rabbitmqdemo.demo.work;

import com.rabbitmq.client.*;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-4-24 21:56
 **/
public class Recveive1 {

    public static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
//        创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        定义队列消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //                有队列 ，就会出发本方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("receive msg [1]:" + new String(body, "utf-8"));
            }
        };
        boolean autoAck = true;
//        监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

    }
}
