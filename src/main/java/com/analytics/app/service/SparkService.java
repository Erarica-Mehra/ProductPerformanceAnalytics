package com.analytics.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.analytics.app.repository.SparkRepository;

@Service
public class SparkService {

	@Autowired SparkRepository sparkRepo;
	
	public void transformData() {
		sparkRepo.transformData();
	}
}
