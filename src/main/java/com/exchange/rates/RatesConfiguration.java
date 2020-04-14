package com.exchange.rates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RatesConfiguration {

	@Bean
	public RestTemplate createRestTemplate() {
		
		return new RestTemplate();
		
	}
	
	
}
