package com.analytics.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.ProductsByName;
import com.analytics.app.model.ProductsBySeller;

@Repository
public interface ProductsByNameRepository extends CassandraRepository<ProductsByName, Serializable> {

	//List<ProductsByName> findByYearAndNameInAndDayBetween(Integer year,String name, String startDate, String endDate);
   //TODO in bound statement
//    @Query("select name , sum(count),avg(quantity) from products_by_name where year = :year  and name in :names group by year,name;")
//	List<ProductsByName> findByYearAndNameIn(Integer year, List<String> names);
    
    List<ProductsByName> findByYearAndNameAndDayBetween(Integer year, String names, String startDate, String enddate);

	List<ProductsByName> findByYearAndNameInAndDayBetween(Integer year, List<String> sellers, String startDate, String endDate);

}
