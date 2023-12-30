package com.sreekanth.mailGuardian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sreekanth.mailGuardian"})
public class MailGuardianApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailGuardianApplication.class, args);
	}

}
