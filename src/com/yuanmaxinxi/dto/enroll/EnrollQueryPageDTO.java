package com.yuanmaxinxi.dto.enroll;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;

public class EnrollQueryPageDTO extends MyBatisQueryPageDTO<Enroll>{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
