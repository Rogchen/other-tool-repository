package com.rogchen.asyc.controller;

import com.rogchen.asyc.service.AsycBuyTicketService;
import com.rogchen.asyc.service.BuyTicketService;
import com.rogchen.asyc.service.CustomAsycBuyTicketService;
import com.rogchen.asyc.service.ExcutesBuyTicketService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

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
        log.info("购票使用时间：" + (System.currentTimeMillis() - now));
        return now+"";
    }
}
