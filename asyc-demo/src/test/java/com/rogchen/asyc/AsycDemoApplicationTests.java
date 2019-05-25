package com.rogchen.asyc;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class AsycDemoApplicationTests {
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		String ticketNo = restTemplate.getForEntity("http://localhost:9091/buyTicket?idNo=1234",String.class).getBody();
		log.info("用户购买票是："+ticketNo);
	}

}
