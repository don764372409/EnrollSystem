package com.yuanmaxinxi.entity.volunteer;

import java.util.ArrayList;
import java.util.List;

public class VolunteerUni {
	private Long id;
	private  String name;
	private List<VolunteerMajor> majors = new ArrayList<>();
	private String address;//所在地
	private String type;//院校类型
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<VolunteerMajor> getMajors() {
		return majors;
	}
	public void setMajors(List<VolunteerMajor> majors) {
		this.majors = majors;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
