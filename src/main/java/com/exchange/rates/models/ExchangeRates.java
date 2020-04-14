package com.exchange.rates.models;

public class ExchangeRates {
	
	private String currency;
	private Double value;
	
	public ExchangeRates(String currency, Double value) {
		this.currency = currency;
		this.value = value;
	}
	
	public ExchangeRates() {}

}
