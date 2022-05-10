package com.lukas.hiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiWebApplication.class, args);

		RestTemplate restTemplate = new RestTemplate();
		String responce = restTemplate.getForObject("https://api.github.com/users/devLukaszMichalak", String.class);
		System.out.println("The responce: \n"+responce);
	}





}
