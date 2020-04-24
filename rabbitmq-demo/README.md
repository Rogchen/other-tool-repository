6种模式
1、简单队列
2、工作队列
3、订阅模式
3.1、广播模式fanout
3.2、路由模式direct
3.3、topic模式
4、rpc模式

# 消费者问题
重复消费：传递一个全局的业务消息id，比如订单号、用户id

消息丢失问题：

答：消息应答和持久化：
Message acknowkedment: 消息应答
autoAck=true，自动应答，一旦消息队列讲消息分配给消费者，消息队列就把消息删除，此时如果消费者挂了，就会出现消息丢失。
解决方法：使用手动应答 autoAck=false（查看workfair模块），手动提交autoAck，可以设置在完成业务后在提交确认ack让对列再删除消息（不影响生产者将消息提交到队列）。

消息队列在运行时候突然挂了，也会出现消息丢失。
解决发方法：使用持久化,设置durable为true，记住已经存在队列是无法直接更改成持久化的，只能通过新建不同队列名或删除已有队列。
```       
#queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
 channel.queueDeclare(QUEUE_NAME, false, false, false, null);
```

# 消息确认机制-生产者这边的

1、事务/confirm

主要解决消息发出去但是不知道有没有到达到到消息队列，默认情况是不知道的。
解决方法：
1、协议 amqp实现了事务机制transtanal
txSelect、txCommit、txRollback
txSelect用户将当前channel设置成事务提交模式

2、confirm 提交确认模式
编程模式：

1、普通： 一条一条发 waitForConfirms();

2、批量的：一批发 waitForConfirms();

3、异步confirm提供一个回调方法