package com.rogchen.asyc.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 加入阻塞队列，另个是采用同步锁来解决问题。
 * @product: IntelliJ IDEA
 * @create by 20-4-26 17:00
 **/
public class RogchenCustomFutoreTask<T> implements Runnable {

    /**
     * 默认无界
     */
    LinkedBlockingDeque<Thread> blockingDeque = new LinkedBlockingDeque<>(10);

    /**
     * 定义一个当前获取状态的游标
     */
    volatile String state = null;
    /**
     * 异步回调
     */
    Callable<T> callable = null;
    /**
     * 返回结果
     */
    T result;

    public RogchenCustomFutoreTask(Callable<T> callable) {
        this.callable = callable;
    }

    /**
     * 如果返回前result未获取结果，将进行队列阻塞，参考lock模式。
     *
     * @return
     */
    public T get() {
//        获取到了result
        if ("end".equalsIgnoreCase(state)) {
            return result;
        }
        blockingDeque.add(Thread.currentThread());
        while (!"end".equalsIgnoreCase(state)) {
            LockSupport.park();
        }
        return result;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            state = "end";
        }
//        任务执行完毕，通知其他线程可以获取返回值了。
        Thread waitThread = blockingDeque.poll();   //可以获取返回值了
        while (waitThread != null) {
            LockSupport.unpark(waitThread);     //解锁
            waitThread = blockingDeque.poll();
        }
    }
}
