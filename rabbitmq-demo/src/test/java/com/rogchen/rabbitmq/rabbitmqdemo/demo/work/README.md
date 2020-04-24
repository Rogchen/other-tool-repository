工作队列队列

多个消费者连接一个队列

**模型：**
```
                    |---->消费者1
生产者--->[队列]--->|
                    |---->消费者2

```

代码写法跟简单其实没差别，就是多个消费者去连接了一个队列

# 消费模式

| 1.轮询分发（round-robin）：

消费者处理消息默认采用轮询分发 消费者消费是均分的。

| 公平分发： 

使用basicQos(perfetch=1)，必须关闭autoAck确认。
```js
# 生产者增加，不影响生产者将消息提交到队列
/**
 * 每个消费者发送确认消息之前，消息队列不发送下一个消息消费者，一次只能处理一个消息
 * 限制发送给同一个消费者不的超过一条数据
 */
int prefetchCount = 1;
channel.basicQos(prefetchCount);

# 每个消费者，同时也需要配置basicQos
channel.basicQos(1);
# 同时需要在回调地方新增
//                手动应答
channel.basicAck(envelope.getDeliveryTag(), false);
# 最后把自动应答关闭
boolean autoAck = false;    //关闭自动应答
//        监听队列
channel.basicConsume(QUEUE_NAME, autoAck, consumer);

```