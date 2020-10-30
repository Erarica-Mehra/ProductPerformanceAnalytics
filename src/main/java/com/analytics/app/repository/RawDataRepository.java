package com.analytics.app.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.analytics.app.model.RawProductData;
import com.analytics.app.model.SimpleTable;
@Repository
public interface RawDataRepository extends CrudRepository<RawProductData, Serializable> {

	//Optional<SimpleTable> findByIdAndName(String id, String name);

	RawProductData save(RawProductData rawaData);
	
	

}
