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
 * @Created Date: 2019/5/25 16:26
 **/
@Log
@Service
public class BaiduService {

    @Autowired
    private RestTemplate restTemplate;

    public String baidu() {
        try {
            TimeUnit.SECONDS.sleep(5l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("访问百度时间：" + System.currentTimeMillis());
        String baidu = restTemplate.getForEntity("https://www.baidu.com", String.class).getBody();
        log.info("访问完成百度时间：" + System.currentTimeMillis());
        return baidu;
    }
}
