package com.yuanmaxinxi.dto;

import com.yuanmaxinxi.entity.major.Major2;

public class MajorQueryPageDTO extends MyBatisQueryPageDTO<Major2>{
	private String name;//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
