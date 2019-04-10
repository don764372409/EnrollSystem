package com.yuanmaxinxi.dto.enroll;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;

public class EnrollQueryPageDTO extends MyBatisQueryPageDTO<Enroll>{
	private String name;
	private Long uId;
	private Long uPId;
	private String property;
	private int uType;
	private Long pId;
	private Long mId;
	private String mNames;
	private String mIds;
	private Long bId;
	private String bIds;
	private Long rank;
	
	
	public String getmNames() {
		return mNames;
	}
	public void setmNames(String mNames) {
		this.mNames = mNames;
	}
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
	}
	public String getbIds() {
		return bIds;
	}
	public void setbIds(String bIds) {
		this.bIds = bIds;
	}
	public int getuType() {
		return uType;
	}
	public void setuType(int uType) {
		this.uType = uType;
	}
	public Long getuId() {
		return uId;
	}
	public Long getuPId() {
		return uPId;
	}
	public void setuPId(Long uPId) {
		this.uPId = uPId;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getmIds() {
		return mIds;
	}
	public void setmIds(String mIds) {
		this.mIds = mIds;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
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
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
}
