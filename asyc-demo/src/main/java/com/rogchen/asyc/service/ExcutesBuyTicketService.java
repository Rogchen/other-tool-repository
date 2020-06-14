package com.rogchen.asyc.service;

import com.rogchen.asyc.config.CommonService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Description: 使用线程池并发访问
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 16:42
 **/
@Log
@Service
public class ExcutesBuyTicketService extends CommonService{

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String buyTicket(String idNo) throws ExecutionException, InterruptedException {
        FutureTask<String> baiduTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return baiduService.baidu();
            }
        });
        FutureTask<String>bilibiliTast = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return bilibiliService.bzhan();
            }
        });
        executorService.submit(baiduTask);
        executorService.submit(bilibiliTast);
        //get请求是阻塞的。
        String bd = baiduTask.get();
        String bl = bilibiliTast.get();
        return System.currentTimeMillis()+"";
    }
}
