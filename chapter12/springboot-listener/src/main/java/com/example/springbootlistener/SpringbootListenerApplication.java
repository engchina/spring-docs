package com.example.springbootlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan({})
public class SpringbootListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootListenerApplication.class, args);
	}

}
