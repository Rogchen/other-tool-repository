package com.rogchen.asyc;

import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/5/16 20:45
 **/
@Log
public class HttpClientTest {


    @Test
    public void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        String ticketNo = restTemplate.getForEntity("http://localhost:9091/buyTicket?idNo=3",String.class).getBody();
        log.info("用户购买票是："+ticketNo);
        log.info("使用异步控制器：============");
        String tn = restTemplate.getForEntity("http://localhost:9091/asycBuyTicket?idNo=3",String.class).getBody();
        log.info("用户异步购买票是："+tn);
    }

}
