package com.app.olx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.app.olx.model.Status;

@Entity(name = "status_list")
public class StatusListEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String status;
	
	public StatusListEntity() {
		super();
	}

	public StatusListEntity(Integer id, String status) {
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
