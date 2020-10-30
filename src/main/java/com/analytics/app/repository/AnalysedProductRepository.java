package com.analytics.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.AnalysedProductData;

@Repository
public interface AnalysedProductRepository extends CassandraRepository<AnalysedProductData, Serializable> {

	List<AnalysedProductData> findByYear(Integer year);

	List<AnalysedProductData> findByYearAndDayBetween(Integer year, String startDate, String endDate);
	
	@Query("SELECT seller, category, COUNT(seller) FROM rfq_entities_by_date ")
	List<AnalysedProductData> getEntitiesCount();
	
}
