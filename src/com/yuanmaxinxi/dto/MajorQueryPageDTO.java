package com.yuanmaxinxi.dto;

import com.yuanmaxinxi.entity.major.Major;

public class MajorQueryPageDTO extends MyBatisQueryPageDTO<Major>{
	private String name;//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
