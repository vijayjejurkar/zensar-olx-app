package com.app.olx.model;

import java.util.Date;

public class Advertise {
	
	private Integer id;
	private String title;
	private Double price;
	private String category;
	private String description;
	private String userName;
	private Date createdDate;
	private Date modifiedDate;
	private String status;
	private String postedBy;
	
	public Advertise() {
		super();
	}

	public Advertise(Integer id, String title, Double price, String category, String description, 
			String userName, Date createdDate, Date modifiedDate, String status, String postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.userName = userName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.postedBy = postedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public String toString() {
		return "Advertise [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
				+ ", description=" + description + ", userName=" + userName + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", status=" + status + ", postedBy=" + postedBy + "]";
	}
}
