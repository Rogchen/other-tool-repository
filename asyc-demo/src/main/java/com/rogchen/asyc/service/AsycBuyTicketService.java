package com.rogchen.asyc.service;

import com.rogchen.asyc.config.CommonService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 直接使用Thread线程测试
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 16:26
 **/
@Service
@Log
public class AsycBuyTicketService extends CommonService {


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
        new Thread(baiduTask).start();
        new Thread(bilibiliTast).start();
        //get请求是阻塞的。
        String bd = baiduTask.get();
        String bl = bilibiliTast.get();
        return System.currentTimeMillis()+"";
    }
}
