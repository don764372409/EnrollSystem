package com.yuanmaxinxi.dto;

import java.util.ArrayList;
import java.util.List;

public class UniNumberDTO {
	private Long uId;
	private String name;//学校名称
	private int uniNumber;//学校重复次数
	private String provinceName;
	private String lv;//等级
	private List<UniAndMajorDTO> uams = new ArrayList<>();//专业出现次数
	
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public int getUniNumber() {
		return uniNumber;
	}
	public void setUniNumber(int uniNumber) {
		this.uniNumber = uniNumber;
	}
	public List<UniAndMajorDTO> getUams() {
		return uams;
	}
	public void setUams(List<UniAndMajorDTO> uams) {
		this.uams = uams;
	}
	@Override
	public String toString() {
		return "UniNumberDTO [uId=" + uId + ", name=" + name + ", uniNumber=" + uniNumber + ", provinceName="
				+ provinceName + ", uams=" + uams + "]";
	}
}
