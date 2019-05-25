package com.rogchen.asyc.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Description: 直接串行访问接口
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/16 20:30
 **/
@Service
@Log
public class BuyTicketService {
    @Autowired
    private BaiduService baiduService;
    @Autowired
    private BilibiliService bilibiliService;

    public String buyTicket(String idNo) throws ExecutionException, InterruptedException {
        String bd = baiduService.baidu();
        String bl = bilibiliService.bzhan();
        return System.currentTimeMillis()+"";
    }

}
