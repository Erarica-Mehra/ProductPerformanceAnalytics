package com.analytics.app.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.ProductsBySeller;

@Repository
public interface ProductsBySellerRepository extends CassandraRepository<ProductsBySeller, Serializable> {


	List<ProductsBySeller> findByYearAndSellerInAndDayBetween(Integer year, List<String> sellers, String startDate, String endDate);
    //TODO add in bound statement
//	@Query("select seller,  AVG(quantity) from products_by_seller where year = :year and seller in :seller group by year,seller")
//	List<ProductsBySeller> getSellerDetails(Integer year, List<String> seller);
	
	List<ProductsBySeller> findByYearAndSellerAndDayBetween(Integer year, String seller, String startDate, String endDate);

	
}
