package com.rogchen.passworddemo;

import com.rogchen.passworddemo.service.EncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptionDecryptionApplicationTests {
	@Autowired
	private EncryptionService encryptionService;

	@Test
	public void contextLoads() {
		//加密
		String param = encryptionService.encryptionByType("{\"aaa\":\"234\"}","0");
		System.out.println(param);
		encryptionService.decryptionByType(param,"0");
	}

}
