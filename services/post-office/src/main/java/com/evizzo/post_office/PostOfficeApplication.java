package com.evizzo.post_office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PostOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostOfficeApplication.class, args);
	}

}
