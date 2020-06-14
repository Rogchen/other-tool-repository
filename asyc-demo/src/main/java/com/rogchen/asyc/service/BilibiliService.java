package com.rogchen.asyc.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/25 16:27
 **/
@Log
@Service
public class BilibiliService {

    @Autowired
    private RestTemplate restTemplate;


    public String bzhan() {
        try {
            TimeUnit.SECONDS.sleep(3l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("访问B站时间：" + System.currentTimeMillis());
        String biying = restTemplate.getForEntity("https://www.bilibili.com/", String.class).getBody();
        log.info("访问B站完成时间：" + System.currentTimeMillis());
        return biying;
    }
}
