package com.exchange.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesApplication.class, args);
	}

}
