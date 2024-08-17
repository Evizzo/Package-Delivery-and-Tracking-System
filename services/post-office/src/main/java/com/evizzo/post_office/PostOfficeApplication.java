package com.evizzo.post_office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostOfficeApplication.class, args);
	}

}
