package com.app.olx.model;

public class StatusList{
	
	private Integer id;
	private String status;
	
	public StatusList() {
		super();
	}

	public StatusList(Integer id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusList [id=" + id + ", status=" + status + "]";
	}

}
