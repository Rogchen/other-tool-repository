package com.rogchen.lock.lockdemo.locks.ReenTrantLock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 可重入锁demo
 * @product: IntelliJ IDEA
 * @create by 20-4-15 22:19
 **/
public class ReenTrantLockDemo implements Lock {

    private static AtomicInteger state = new AtomicInteger(0);
    private Thread owerThread = null;
    private LinkedBlockingDeque<Thread> writerDeques = new LinkedBlockingDeque<>();

    /**
     * @Description 如何变成可重入锁，首先判断是否是当前线程，是就state+1
     * @Author Rogchen
     * @Date 下午2:43 20-4-16
     * @Param []
     * @Return boolean
     **/
    @Override
    public boolean tryLock() {
        //获取锁
        if (state.get() == 0) {
            if (state.compareAndSet(0, 1)) {//state.compareAndSet(0, 1);// cas操作，保证只有一个线程能成功
                owerThread = Thread.currentThread();    // 记录上锁的线程
                return true;
            }
        } else if (owerThread == Thread.currentThread()) {  //可重入
            state.set(state.get() + 1);
            return true;
        }
        return false;
    }

    /* *
     * @Description
     * @Author Rogchen
     * @Date 下午2:41 20-4-16
     * @Param []
     * @Return void
     **/
    @Override
    public void lock() {
        if (!tryLock()) { //上锁失败-需要等待-把线程加入队列
            writerDeques.add(Thread.currentThread());
//            需要循环去尝试获取锁
            while (true) {
                if (tryLock()) {
                    writerDeques.poll();    //一旦队列的线程抢到锁就把自己移除
                    return;
                } else {
                    LockSupport.park();     //等待  ,唤醒的目的就是为了接着强锁
                }
            }
        }
    }

    @Override
    public void unlock() {
//        判断是否是当前线程来解锁
        if (Thread.currentThread() != owerThread) {
            throw new IllegalStateException("非法解锁，当前线程无法进行操作～");
        }
        if (state.decrementAndGet() == 0) {
            owerThread = null;  //提前进行gc
//          通知其他线程拿锁-取出队列(fifo)第一个，但是不一定是它拿到锁，这就是不公平锁。公平锁肯定是第一个拿到锁
            Thread otherThread = writerDeques.peek();
            if (otherThread != null)
                LockSupport.unpark(otherThread);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
