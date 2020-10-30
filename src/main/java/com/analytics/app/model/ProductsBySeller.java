package com.analytics.app.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("products_by_seller")
public class ProductsBySeller implements Serializable {

	private static final long serailVersionLongUID = 1L;
	@PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@Column("year")
	private Integer year;

	@PrimaryKeyColumn(name = "seller", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("seller")
	private String seller;

	@PrimaryKeyColumn(name = "day", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	@Column("day")
	private Date day;

	@PrimaryKeyColumn(name = "name", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
	@Column("name")
	private String name;

	@PrimaryKeyColumn(name = "id", ordinal = 4, type = PrimaryKeyType.CLUSTERED)
	@Column("id")
	private Long id;
	
	@Column("rating")
	private Integer rating;

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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
