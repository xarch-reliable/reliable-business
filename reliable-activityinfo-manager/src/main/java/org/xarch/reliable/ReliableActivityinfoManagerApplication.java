package org.xarch.reliable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReliableActivityinfoManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableActivityinfoManagerApplication.class, args);
	}

}
