package com.analytics.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytics.app.model.RawProductData;
import com.analytics.app.repository.AnalyticsRepository;
import com.analytics.app.repository.RawDataRepository;

@Service
public class RawDataService {
	
	@Autowired RawDataRepository rawDataRepository;

	public RawProductData save(RawProductData rawaData) {
		return rawDataRepository.save(rawaData);
	}

	public Iterable<RawProductData> findRecords() {
		
		return rawDataRepository.findAll();
	}
	
}
