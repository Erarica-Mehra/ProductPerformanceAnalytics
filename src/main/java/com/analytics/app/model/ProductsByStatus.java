package com.analytics.app.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("products_by_status")
public class ProductsByStatus implements Serializable {

	private static final long serailVersionLongUID = 1L;
	@PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@Column("year")
	private Integer year;

	@PrimaryKeyColumn(name = "status", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("status")
	private String status;

	@PrimaryKeyColumn(name = "day", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	@Column("day")
	private Date day;

	@PrimaryKeyColumn(name = "id", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
	@Column("id")
	private String id;

	@Column("quantity")
	private Integer quantity;

	@Column("count")
	private Integer count;

	@Column("price")
	private Double price;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
