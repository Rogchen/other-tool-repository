package com.rogchen.ms.singletonFactory;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 单例模式 -main方法测试
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/11/29 10:58
 **/
public class SinletonTest {

    public static final CountDownLatch latch = new CountDownLatch(300);

    /**
     * 需要频繁的进行创建和销毁的对象；
     * 创建对象时耗时过多或耗费资源过多，但又经常用到的对象；
     * 工具类对象；
     * 频繁访问数据库或文件的对象。
     * @param args
     */
    public static void main(String[] args) {
        SingletonStatic factory = SingletonStatic.getInstance();
        System.out.println(factory);
        SingletonStatic factory1 = SingletonStatic.getInstance();
        System.out.println(factory1);

        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                latch.countDown();
                SingletonStatic lhs = SingletonStatic.getInstance();
                System.out.println(lhs);
                System.out.println(Thread.currentThread().getName()+"当前时间："+new Date().toLocaleString());
            }).start();
        }
    }
}
