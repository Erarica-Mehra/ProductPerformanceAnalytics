package com.analytics.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytics.app.model.ProductsByName;
import com.analytics.app.model.ProductsBySeller;
import com.analytics.app.repository.AnalyticsRepository;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.Row;

@Service
public class AnalyticsService {

	@Autowired
	private AnalyticsRepository analyticsRepo;

	public List<ProductsBySeller> sellerWiseAnalysis(int year, List<String> sellers, Date startDate,
			Date endDate) throws ParseException {
		LocalDate start = LocalDate.fromYearMonthDay((startDate.getYear()+1900), (startDate.getMonth() +1 ) , startDate.getDate());
		LocalDate end = LocalDate.fromYearMonthDay((endDate.getYear()+1900), (endDate.getMonth() + 1) , endDate.getDate());
		List<Row> list = analyticsRepo.getAnalysedSellers(year, sellers, start, end);
		ProductsBySeller productsBySeller = new ProductsBySeller();
		List<ProductsBySeller> sellersList = new ArrayList<ProductsBySeller>();
		for (Row row : list) {
			productsBySeller.setSeller(row.getString("seller"));
			productsBySeller.setCount(row.getInt("system.sum(count)"));
			productsBySeller.setQuantity(row.getInt("system.avg(quantity)"));
			sellersList.add(productsBySeller);
		}
		return sellersList;
	}

	public List<ProductsByName> productAnalysisOfSeller(Integer year, List<String> productsNames, Date startDate,
			Date endDate) {
		LocalDate start = LocalDate.fromYearMonthDay((startDate.getYear()+1900), (startDate.getMonth() +1 ) , startDate.getDate());
		LocalDate end = LocalDate.fromYearMonthDay((endDate.getYear()+1900), (endDate.getMonth() + 1) , endDate.getDate());
		List<Row> list = analyticsRepo.getAnalysedProductsPerSeller(year, productsNames, start, end);
		ProductsByName productsByName = new ProductsByName();
		List<ProductsByName> productsList = new ArrayList<ProductsByName>();
		for (Row row : list) {
			productsByName.setName(row.getString("name"));
			productsByName.setCount(row.getInt("system.sum(count)"));
			productsByName.setQuantity(row.getInt("system.avg(quantity)"));
			productsList.add(productsByName);
		}
		return productsList;
	}
	

}
