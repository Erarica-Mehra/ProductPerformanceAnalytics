package com.analytics.app.repository;

import java.util.List;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.Row;

public interface AnalyticsRepository  {
	
	List<Row> getAnalysedSellers(int year, List<String> sellers, LocalDate startDate, LocalDate endDate);

	List<Row> getAnalysedProductsPerSeller(Integer year, List<String> productsNames, LocalDate start, LocalDate end);

	

}
