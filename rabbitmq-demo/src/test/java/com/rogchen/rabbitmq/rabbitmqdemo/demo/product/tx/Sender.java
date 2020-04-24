package com.rogchen.rabbitmq.rabbitmqdemo.demo.product.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-4-24 21:48
 **/
public class Sender {
    public static final String QUEUE_NAME = "test_queue_produce_tx";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//        获取一个连接
        Connection connection = ConnectionUtils.getConnection();
//        获取一个通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "生产者生产消息了。。。。。。tx";
        try {
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//            提交失败,触发回滚
//            int i = 1 / 0;
            System.out.println("tx sender send [1] :" + message);
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
            System.out.println("sender send rollback");
        }
//        发送的消息内容
        channel.close();
        connection.close();
    }
}
