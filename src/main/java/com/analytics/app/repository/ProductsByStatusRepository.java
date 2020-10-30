package com.analytics.app.repository;

import java.io.Serializable;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.ProductsByStatus;

@Repository
public interface ProductsByStatusRepository extends CassandraRepository<ProductsByStatus, Serializable> {

	@Query("select count(id) from products_by_status where year = :year and status = :status")
	Integer getProductCountByStatus(int year, String status);
}
