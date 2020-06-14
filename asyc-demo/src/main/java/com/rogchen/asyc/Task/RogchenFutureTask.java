package com.rogchen.asyc.Task;

import org.springframework.util.StringUtils;

import java.util.concurrent.*;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 17:05
 **/
public class RogchenFutureTask<V> implements Runnable, Future<V> {

    private Callable<V> callable;

    public RogchenFutureTask(Callable<V> callable) {
        this.callable = callable;
    }

    V result = null;

    @Override
    public void run() {
        try {
            result = callable.call();
            synchronized (this){
                this.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        if(!StringUtils.isEmpty(result)){
            return result;
        }
        //阻塞住当前线程
        synchronized (this){
            this.wait();
        }
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }


}
