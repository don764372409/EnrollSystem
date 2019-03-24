package com.yuanmaxinxi.entity.volunteer;

import java.util.List;

public class VolunteerUni {
	private Long id;
	private  String name;
	private List<VolunteerMajor> majors;
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
	
}
