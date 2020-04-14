package com.exchange.rates.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.exchange.rates.entity.RatesEntity;
import com.exchange.rates.models.Rate;
import com.exchange.rates.repository.RatesRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/rates")
public class RatesController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RatesRepository ratesRepo;

	@GetMapping
	public List<Rate> list() {
		List<Rate> rates = new ArrayList<>();
		return rates;
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public void create() {
		   Calendar calNow = Calendar.getInstance();
		   int month = calNow.get(Calendar.MONTH)+1;
		   int year = calNow.get(Calendar.YEAR);
		   for(int i =0; i<6; i++) {
			   if((month-i)==0) {
				   month = 12+i;
				   year = calNow.get(Calendar.YEAR) - 1;
			   }		   
				Rate rate = restTemplate.getForObject(
						"https://api.ratesapi.io/api/" + year + "-"+(month-i)+"-" + calNow.get(Calendar.DATE), Rate.class
						);
				rate.getRates().forEach((k,v) -> {
					RatesEntity rateEntity = new RatesEntity(k, v, rate.getDate());
					ratesRepo.save(rateEntity);
				});
		   }
	}
	
	@GetMapping("/{id}")
	public Map<String, ArrayList<RatesEntity>> getRate(@PathVariable("id") long id) {
		Map<String, ArrayList<RatesEntity>> ratesByDateMap =  new HashMap<String, ArrayList<RatesEntity>>();
		List<RatesEntity> rateFetched=ratesRepo.getRatesByLastSixMonth();
		rateFetched.stream().forEach(rate1 -> {		
			if(ratesByDateMap.containsKey(rate1.getDate().toString())) {
				ratesByDateMap.get(rate1.getDate().toString()).add(rate1);
			} else {
				ArrayList<RatesEntity> rates = new ArrayList<RatesEntity>(Arrays.asList(rate1));
				ratesByDateMap.put(rate1.getDate().toString(), rates);	
			}
		});
		return ratesByDateMap;
	}

}


