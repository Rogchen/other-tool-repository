订阅模式之广播模式fanout

广播模式
```js
                       |[队列1]-->消费者1
生产者--->[交换机]---> |[队列2]-->消费者2
                       |[队列3]-->消费者3
                       |[无数个绑定交换机的队列] 
```

可以在生产者就直接将队列、交换机直接进行绑定，如果不绑定就无法把在队列上看到消息，
```js
//        声明交换机-设置模式为广播模式
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = "生产者生产消息了。。。。。。";
//        创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        将队列跟交换机绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
```
广播模式就是在生产者定义一个交换机。可以进行全网广播，跟路由的全网广播一个样子。