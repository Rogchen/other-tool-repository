package com.rogchen.asyc.service;

import com.rogchen.asyc.Task.RogchenFutureTask;
import com.rogchen.asyc.config.CommonService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 使用线程池异步并发访问接口-通过自定义的futureTask
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 17:08
 **/
@Service
@Log
public class CustomAsycBuyTicketService extends CommonService {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public String buyTicket(String idNo) throws ExecutionException, InterruptedException {
        RogchenFutureTask<String> baidu = new RogchenFutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return baiduService.baidu();
            }
        });

        RogchenFutureTask<String> bili = new RogchenFutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return bilibiliService.bzhan();
            }
        });
        executorService.submit(baidu);
        executorService.submit(bili);
        String bd = baidu.get();
        String bl = bili.get();
        return System.currentTimeMillis()+"";
    }
}
