package com.app.olx.model;

public class AdvertiseCategory {
	
	private Integer id;
	private String category;
	
	public AdvertiseCategory() {
		super();
	}

	public AdvertiseCategory(Integer id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AdvertiseCategory [id=" + id + ", category=" + category + "]";
	}

}
