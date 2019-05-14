package com.yuanmaxinxi.entity.enroll;

public class EnrollMajor {
	private Long mId;
	private String name;
	private String no="";
	private Integer minRank;
	private Integer avgRank;
	private Integer maxRank;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getMinRank() {
		return minRank;
	}
	public void setMinRank(Integer minRank) {
		this.minRank = minRank;
	}
	public Integer getAvgRank() {
		return avgRank;
	}
	public void setAvgRank(Integer avgRank) {
		this.avgRank = avgRank;
	}
	public Integer getMaxRank() {
		return maxRank;
	}
	public void setMaxRank(Integer maxRank) {
		this.maxRank = maxRank;
	}
	
}
