package com.exchange.rates.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exchange.rates.entity.RatesEntity;

@Repository
public interface RatesRepository extends JpaRepository<RatesEntity, Long> {
	
	@Query(value="select * from rates r WHERE r.date >= DATEADD(MONTH, -6, current_date)"
			, nativeQuery=true)
	public ArrayList<RatesEntity> getRatesByLastSixMonth();
}