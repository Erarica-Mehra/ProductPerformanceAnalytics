package com.analytics.app.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.AnalysedProductData;
import com.datastax.driver.core.LocalDate;

@Repository
public interface TestRepository extends CassandraRepository<AnalysedProductData, Serializable> {

	List<AnalysedProductData> findByYear(Integer year);

	List<AnalysedProductData> findByYearAndDayBetween(Integer year, String startDate, String endDate);
	
	@Query("SELECT seller, category, COUNT(seller) FROM rfq_entities_by_date ")
	List<AnalysedProductData> getEntitiesCount();
	
}
