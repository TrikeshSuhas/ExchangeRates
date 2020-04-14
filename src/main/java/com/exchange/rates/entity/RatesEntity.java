package com.exchange.rates.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rates")
public class RatesEntity {
	   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	   private Long id;
	   
	   @Column
	   private String currency;
	   
	   @Column
	   private Double rate;
	   
	   @Column
	   private Date date;

	   
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public RatesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RatesEntity(String currency, Double rate, Date date) {
		super();
		this.currency = currency;
		this.rate = rate;
		this.date = date;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public Double getRate() {
		return rate;
	}


	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
	   
	   
}

