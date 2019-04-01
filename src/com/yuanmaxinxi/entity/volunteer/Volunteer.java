package com.yuanmaxinxi.entity.volunteer;

import java.util.ArrayList;
import java.util.List;

public class Volunteer {
	private Long  proId;
	private String batch;
	private int number;
	private List<VolunteerUni> unis = new ArrayList<>();
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<VolunteerUni> getUnis() {
		return unis;
	}
	public void setUnis(List<VolunteerUni> unis) {
		this.unis = unis;
	}
	
}
