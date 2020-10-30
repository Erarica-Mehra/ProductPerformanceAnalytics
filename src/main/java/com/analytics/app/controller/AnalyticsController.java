package com.analytics.app.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analytics.app.model.AnalysedProductData;
import com.analytics.app.model.ProductsByName;
import com.analytics.app.model.ProductsBySeller;
import com.analytics.app.model.RawProductData;
import com.analytics.app.repository.AnalysedProductRepository;
import com.analytics.app.repository.ProductsByNameRepository;
import com.analytics.app.repository.ProductsBySellerRepository;
import com.analytics.app.repository.ProductsByStatusRepository;
import com.analytics.app.service.AnalyticsService;
import com.analytics.app.service.RawDataService;
import com.analytics.app.service.SparkService;

@RestController
@RequestMapping("/cassandra")
public class AnalyticsController {

	@Autowired
	private RawDataService rawDataService;

	@Autowired
	private AnalysedProductRepository analysedProductRepository;

	@Autowired
	private ProductsBySellerRepository sellerRepo;

	@Autowired
	private ProductsByNameRepository namesRepo;

	@Autowired
	private AnalyticsService analyticsService;

	@Autowired
	private ProductsByStatusRepository statusRepo;
	
	@Autowired SparkService sparkService;

	@PostMapping(path = "/save")
	public ResponseEntity<RawProductData> saveRawData(@RequestBody RawProductData rawaData) {
		RawProductData savedData = rawDataService.save(rawaData);
		return new ResponseEntity<>(savedData, HttpStatus.OK);
	}

	@GetMapping(path = "/products/{year}")
	public ResponseEntity<List<AnalysedProductData>> findAnalysedProductsByYear(@PathVariable("year") Integer year,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<AnalysedProductData> analysedProduct = analysedProductRepository.findByYearAndDayBetween(year, startDate,
				endDate);
		return new ResponseEntity<>(analysedProduct, HttpStatus.OK);
	}

	@GetMapping(path = "/sellerWiseAnalysis/{year}")
	public ResponseEntity<List<ProductsBySeller>> sellerWiseAnalysis(@PathVariable("year") Integer year,
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
		String s = startDate.toString();
		String e = endDate.toString();
		List<AnalysedProductData> productsList = analysedProductRepository.findByYearAndDayBetween(year, s, e);
		List<String> sellers = new ArrayList<String>();
		if (!productsList.isEmpty())
			productsList.forEach(p -> sellers.add(p.getSeller()));
		List<ProductsBySeller> sellersList = new ArrayList<>();
		try {
			sellersList = analyticsService.sellerWiseAnalysis(year, sellers, startDate, endDate);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(sellersList, HttpStatus.OK);
	}

	@GetMapping(path = "/productAnalysisOfASeller/{year}/{seller}")
	public ResponseEntity<List<ProductsByName>> productAnalysisOfASeller(@PathVariable("year") Integer year,
			@PathVariable("seller") String seller, @RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate) {
		List<ProductsBySeller> productInfoOfSeller = sellerRepo.findByYearAndSellerAndDayBetween(year, seller,
				startDate.toString(), endDate.toString());
		List<String> productsNames = new ArrayList<String>();
		if (!productInfoOfSeller.isEmpty())
			productInfoOfSeller.forEach(p -> productsNames.add(p.getName()));
		List<ProductsByName> productList = new ArrayList<>();
		productList = analyticsService.productAnalysisOfSeller(year, productsNames, startDate, endDate);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping(path = "/getTotal/{year}/{status}")
	public ResponseEntity<Integer> getTotalProductsByStatus(@PathVariable("year") Integer year,
			@PathVariable("status") String status) {
		int count = statusRepo.getProductCountByStatus(year, status);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@GetMapping(path = "/sellerInfo/{year}/{seller}")
	public ResponseEntity<List<ProductsBySeller>> getSellerInfo(@PathVariable("year") Integer year,
			@PathVariable("seller") String seller, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		List<ProductsBySeller> sellersInfo = sellerRepo.findByYearAndSellerAndDayBetween(year, seller, startDate,
				endDate);
		return new ResponseEntity<>(sellersInfo, HttpStatus.OK);
	}

	@GetMapping(path = "/viewProducts/{year}")
	public ResponseEntity<List<ProductsByName>> findProductsByName(@PathVariable("year") Integer year,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		List<AnalysedProductData> productsList = analysedProductRepository.findByYearAndDayBetween(year, startDate,
				endDate);
		List<String> sellers = new ArrayList<String>();
		if (!productsList.isEmpty())
			productsList.forEach(p -> sellers.add(p.getSeller()));
		List<ProductsBySeller> sellersList = sellerRepo.findByYearAndSellerInAndDayBetween(year, sellers, startDate,
				endDate);
		List<String> names = new ArrayList<String>();
		if (!sellersList.isEmpty())
			sellersList.forEach(s -> sellers.add(s.getName()));
		List<ProductsByName> namesList = namesRepo.findByYearAndNameInAndDayBetween(year, names, startDate, endDate);
		return new ResponseEntity<>(namesList, HttpStatus.OK);
	}

	@GetMapping(path = "/productInfo/{year}/{name}")
	public ResponseEntity<List<ProductsByName>> getNameInfo(@PathVariable("year") Integer year,
			@PathVariable("name") String name, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		List<ProductsByName> sellersInfo = namesRepo.findByYearAndNameAndDayBetween(year, name, startDate, endDate);
		return new ResponseEntity<>(sellersInfo, HttpStatus.OK);
	}

	@RequestMapping("/refresh")
	public ResponseEntity<String> home() {
		sparkService.transformData();
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
}
