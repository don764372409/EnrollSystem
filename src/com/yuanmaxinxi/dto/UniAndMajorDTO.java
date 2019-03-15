package com.yuanmaxinxi.dto;

public class UniAndMajorDTO {
	private Long mId;
	private String name;
	private int minNumber;//最低分
	private int maxNumber;//最高分
	private int number;//当前专业出现次数
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "UniAndMajorDTO [mId=" + mId + ", name=" + name + ", minNumber=" + minNumber + ", maxNumber=" + maxNumber
				+ ", number=" + number + "]";
	}
}
