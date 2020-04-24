package com.rogchen.rabbitmq.rabbitmqdemo.demo.ps.topic;

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
public class Recveive2 {

    public static final String EXCHANGE_NAME = "exchange_name_ps_topic";
    public static final String QUEUE_NAME = "test_queue_ps_topic_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
////        定义交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
//        创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        将队列和交换机绑定,指定路由键是error
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "goods.#");
        channel.basicQos(1);
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
                System.out.println("receive msg [2]:" + new String(body, "utf-8"));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        boolean autoAck = false;
//        监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

    }
}
