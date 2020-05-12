package com.rogchen.asyc;

import com.rogchen.asyc.Task.RogchenCustomFutoreTask;
import org.assertj.core.util.DateUtil;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-4-26 17:03
 **/
public class CustomFutureTask {

    @Test
    public void 使用自定义FutureTask() {
        ExecutorService executorService = Executors.newFixedThreadPool(15);
        RogchenCustomFutoreTask<String> futoreTask1 = new RogchenCustomFutoreTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);  //模拟请求需要5秒
//                开始购票，返回票信息 httputil
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ticketId-", "122222222234dd32");
                jsonObject.put("name", "2");
                jsonObject.put("date", DateUtil.formatAsDatetime(new Date()));
                return jsonObject.toString();
            }
        });
        executorService.submit(futoreTask1);
        System.out.println(Thread.currentThread() + "出票成功：-------------票信息：" + futoreTask1.get());
        executorService.shutdown();
    }


    @Test
    public void 多次测试() throws InterruptedException {

        Thread thread = null;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    使用自定义FutureTask();
                }
            });
            thread.start();
        }
        TimeUnit.SECONDS.sleep(10);
    }


}
