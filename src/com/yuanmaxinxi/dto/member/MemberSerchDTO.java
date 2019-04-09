package com.yuanmaxinxi.dto.member;

import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.entity.enroll.Enroll;

public class MemberSerchDTO {
	private String year;//招生日期
	private Long uId;//学校ID
	private String name;//学校名称
	private List<Enroll> list = new ArrayList<>();
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Enroll> getList() {
		return list;
	}
	public void setList(List<Enroll> list) {
		this.list = list;
	}
}
