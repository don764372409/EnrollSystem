package com.yuanmaxinxi.entity.enroll;

import java.util.List;

import com.yuanmaxinxi.entity.university.University;

public class UniEnrollMajor {
	private University uni;
	private List<EnrollMajor> ems;
	public University getUni() {
		return uni;
	}
	public void setUni(University uni) {
		this.uni = uni;
	}
	public List<EnrollMajor> getEms() {
		return ems;
	}
	public void setEms(List<EnrollMajor> ems) {
		this.ems = ems;
	}
	
	
}
