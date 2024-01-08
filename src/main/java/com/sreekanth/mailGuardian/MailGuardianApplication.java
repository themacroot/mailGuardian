package com.sreekanth.mailGuardian;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sreekanth.mailGuardian"})
public class MailGuardianApplication {
	
	
	  @Value("${NAME:World}")
	  String name;

	public static void main(String[] args) {
		SpringApplication.run(MailGuardianApplication.class, args);
	}

}
