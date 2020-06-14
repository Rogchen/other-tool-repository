package com.rogchen.asyc.controller;

import com.rogchen.asyc.service.AsycBuyTicketService;
import com.rogchen.asyc.service.BuyTicketService;
import com.rogchen.asyc.service.CustomAsycBuyTicketService;
import com.rogchen.asyc.service.ExcutesBuyTicketService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/16 20:24
 **/
@RestController
@Log
public class BuyTicketController {

    @Autowired
    private BuyTicketService buyTicketService;
    @Autowired
    private AsycBuyTicketService asycBuyTicketService;
    @Autowired
    private ExcutesBuyTicketService excutesBuyTicketService;
    @Autowired
    private CustomAsycBuyTicketService customAsycBuyTicketService;

    /**
     * 线程阻塞更加清楚查看同步跟异步的区别
     * @param idNo
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping(value = "buyTicket")
    public String buyTicket(String idNo) throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        switch (idNo) {
            case "1":
                asycBuyTicketService.buyTicket(idNo);
                break;
            case "2":
                excutesBuyTicketService.buyTicket(idNo);
                break;
            case "3":
                customAsycBuyTicketService.buyTicket(idNo);
                break;
            default:
                buyTicketService.buyTicket(idNo);
                break;
        }
        TimeUnit.SECONDS.sleep(5);
        log.info("购票使用时间：" + (System.currentTimeMillis() - now));
        return now+"";
    }

    /**
     * 差别就是：异步tomcat主线程已经返回了，释放了这个线程。所以用时只要3ms
     * @param idNo
     * @return
     */
    @GetMapping("asycBuyTicket")
    public Callable<String> asycBuyTicket(String idNo){
        long now = System.currentTimeMillis();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                switch (idNo) {
                    case "1":
                        asycBuyTicketService.buyTicket(idNo);
                        break;
                    case "3":
                        customAsycBuyTicketService.buyTicket(idNo);
                        break;
                    case "2":
                        excutesBuyTicketService.buyTicket(idNo);
                        break;
                    default:
                        buyTicketService.buyTicket(idNo);
                        break;
                }
                TimeUnit.SECONDS.sleep(5);
                return now+"";
            }
        };
        log.info("购票使用时间：" + (System.currentTimeMillis() - now));
        return callable;
    }
}
