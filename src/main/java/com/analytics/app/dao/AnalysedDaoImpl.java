package com.analytics.app.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

import org.springframework.stereotype.Repository;

import com.analytics.app.model.ProductsBySeller;
import com.analytics.app.repository.AnalyticsRepository;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@Repository
public class AnalysedDaoImpl implements AnalyticsRepository {

	private Cluster cluster;
	private Session session;
	private PreparedStatement statement;
	private PreparedStatement productsBySellerInfo;
	private PreparedStatement productsByNameStatement;

	public void fetchSellers() {
		session = getConnection();
		statement = session.prepare("select seller from rfq_entities_by_date where time_series_key = 2020");
		BoundStatement boundStatement = null;
		try {
			boundStatement = statement.bind();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet rs = session.execute(boundStatement);
		List<Row> list = rs.all().subList(0, 10);
		System.out.println(list.size());
		System.out.println(list.get(0).getInt("seller"));
	}

	private void executeStatement(Statement query) {
		if (session != null && query != null) {
			try {
				session.execute(query);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Session getConnection() {
		cluster = Cluster.builder().addContactPoint("localhost").withCredentials("aerobay", "aerobay").build();
		CodecRegistry myCodecRegistry = cluster.getConfiguration().getCodecRegistry();
		//myCodecRegistry.register(LocalDateCodec.instance);
		session = cluster.connect("aerobay");
		return session;
	}

	@Override
	public List<Row> getAnalysedSellers(int year, List<String> sellers, LocalDate startDate, LocalDate endDate) {
		session = getConnection();
		productsBySellerInfo = session.prepare("select seller, sum(count),avg(quantity) from products_by_seller where year = ? and seller in ? and day > ? and day < ? group by year,seller");
		BoundStatement boundStatement = null;
		try {
			boundStatement = productsBySellerInfo.bind(year, sellers, startDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet rs = session.execute(boundStatement);
		List<Row> list = rs.all();
		
		return list;
	}

	@Override
	public List<Row> getAnalysedProductsPerSeller(Integer year, List<String> productsNames, LocalDate start,
			LocalDate end) {
		session = getConnection();
		productsByNameStatement = session.prepare("select name, sum(count),avg(quantity) from products_by_name where year = ? and name in ? and day > ? and day < ? group by year,name");
		BoundStatement boundStatement = null;
		try {
			boundStatement = productsByNameStatement.bind(year, productsNames, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet rs = session.execute(boundStatement);
		List<Row> list = rs.all();
		return list;
	}
}
