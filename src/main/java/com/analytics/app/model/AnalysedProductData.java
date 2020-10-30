package com.analytics.app.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("analysed_product")
public class AnalysedProductData implements Serializable {

	private static final long serailVersionLongUID = 1L;
	@PrimaryKeyColumn(name = "year", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	@Column("year")
	private Integer year;

	@PrimaryKeyColumn(name = "day", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	@Column("day")
	private Date day;

	@PrimaryKeyColumn(name = "seller", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	@Column("seller")
	private String seller;

	@PrimaryKeyColumn(name = "category", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
	@Column("category")
	private String category;

	@PrimaryKeyColumn(name = "name", ordinal = 4, type = PrimaryKeyType.CLUSTERED)
	@Column("name")
	private String name;

	@PrimaryKeyColumn(name = "id", ordinal = 5, type = PrimaryKeyType.CLUSTERED)
	@Column("id")
	private Long id;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
