package com.ylzinfo.ms.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ylzinfo.ms")
public class YlzimInfoWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(YlzimInfoWebappApplication.class, args);
	}
}
