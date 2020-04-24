exchange 交换机

一方面接受生产者提供的消息，一方面给队列推送消息，

1、匿名转发""：
之前在简单模式/工作队列上第一个参数
`channel.basicPublish("", QUEUE_NAME, null, message.getBytes());`
2、fanout（不处理路由键） 广播模式

3、direct 路由模式routing