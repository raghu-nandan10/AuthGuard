package com.jwt.jsonwebtokenproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication


public class JsonwebtokenprojectApplication {



	public static void main(String[] args) {
		SpringApplication.run(JsonwebtokenprojectApplication.class, args);
	}

}
