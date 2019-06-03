package com.rogchen.ms.singletonFactory;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
* @Description 单例模式-懒汉式[不可用] :线程不安全
 * <p>当一个线程刚好进入 if()另个线程也进入，就产生了多个实例</p>
* @Author Rogchen rogchen128@gmail.com
* @Created 2018/11/29 11:35
**/
public class SingletonLhsDmk {

    private static SingletonLhsDmk singleton;

    private SingletonLhsDmk() {}

    public static SingletonLhsDmk getInstance() {
        if (singleton == null) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (SingletonLhsDmk.class) {
                singleton = new SingletonLhsDmk();
            }
        }
        return singleton;
    }


    private static final CountDownLatch latch = new CountDownLatch(300);

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                latch.countDown();
                SingletonLhsDmk lhs = SingletonLhsDmk.getInstance();
                System.out.println(lhs);
                System.out.println(Thread.currentThread().getName()+"当前时间："+new Date().toLocaleString());
            }).start();
        }
    }
}