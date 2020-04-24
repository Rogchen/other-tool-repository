package com.rogchen.rabbitmq.rabbitmqdemo.demo.product.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rogchen.rabbitmq.rabbitmqdemo.demo.ConnectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 异步回调确认
 * @product: IntelliJ IDEA
 * @create by 20-4-25 01:09
 **/
public class AsyncSender3 {

    public static final String QUEUE_NAME = "test_queue_confirm";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

//        跟tx是互斥的
        channel.confirmSelect();//开启confirm
        String message = "生产者生产消息了。。。。。。confirm_asyn--";
//        设置回调确认集合
        SortedSet<Long> sortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multipe) throws IOException {
                if (multipe) {
                    System.out.println("--handleack--multipe-ack");
                    sortedSet.headSet((deliveryTag + 1)).clear();
                } else {        //植入重试什么的。
                    System.out.println("--handleack--multipe:----" + multipe);
                    sortedSet.remove(deliveryTag);
                }
            }
//            回调失败nack，
            @Override
            public void handleNack(long deliveryTag, boolean multipe) throws IOException {
                if (multipe) {
                    System.out.println("--handleack--multipe-nack");
                    sortedSet.headSet((deliveryTag + 1)).clear();
                } else {
                    System.out.println("--handleack--multipe-----:" + multipe);
                    sortedSet.remove(deliveryTag);
                }
            }
        });
        while (true) {
            long seqnext = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            sortedSet.add(seqnext);
        }
    }
}
