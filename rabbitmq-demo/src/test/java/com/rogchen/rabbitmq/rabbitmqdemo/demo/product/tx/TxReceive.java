package com.rogchen.rabbitmq.rabbitmqdemo.demo.product.tx;

import com.rabbitmq.client.*;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-4-25 00:41
 **/
public class TxReceive {
    public static final String QUEUE_NAME = "test_queue_produce_tx";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        获取一个连接
        Connection connection = ConnectionUtils.getConnection();
//        获取一个通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到消息了：" + new String(body, "utf-8"));

            }
        };
//        自动提交
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }
}
