package com.rogchen.lock.lockdemo.locks.ReenTrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-4-15 22:22
 **/
public class LockDemo {

    private static Lock lock = new ReenTrantLockDemo();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        System.out.println("第一次上锁");
        lock.lock();
        System.out.println("同步-第二次上锁");
        new Thread(() -> {
            System.out.println("异步：第3线程尝试获取锁");
            lock.lock();
            System.out.println("异步：第3线获取到了锁进行输出");
            lock.unlock();
        }).start();

        TimeUnit.SECONDS.sleep(3);
        lock.unlock();
        lock.unlock();
        System.out.println("获取锁结束");
    }
}
