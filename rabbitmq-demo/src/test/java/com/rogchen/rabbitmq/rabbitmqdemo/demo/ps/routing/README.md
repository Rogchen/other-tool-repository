订阅模式之路由模式

路由模型：
```js
                       |[路由键info]---[队列1]-->消费者1
生产者--->[交换机]---> |[路由键debu]---[队列1]-->消费者1
                       |[路由键abc]---[队列1]-->消费者1
                       |[路由键abc]---[队列2]-->消费者2
```

交换机通过路由键绑定队列,相同路由键可以绑定不同队列，不同路由键可以绑定相同队列

不足：需要全次匹配，无法进行归档分类。