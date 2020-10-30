package com.analytics.app.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("raw_product_data")
public class RawProductData implements Serializable {
	private static final long serailVersionLongUID = 1L;

	@PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@Column("year")
	private Integer year;

	@PrimaryKeyColumn(name = "month", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("month")
	private Integer month;

	@PrimaryKeyColumn(name = "timestamp", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	@Column("timestamp")
	private Timestamp timestamp;

	@Column("id")
	private Long id;

	@Column("category")
	private String category;
	@Column("seller")
	private String seller;
	@Column("name")
	private String name;
	@Column("quantity")
	private Integer quantity;
	@Column("price")
	private Double price;
	@Column("rating")
	private Integer rating;
	@Column("status")
	private String status;
	@Column("description")
	private String description;
	@Column("user")
	private String user;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
