package com.yuanmaxinxi.dto.enroll;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;

public class EnrollQueryPageDTO extends MyBatisQueryPageDTO<Enroll>{
	private String name;
	private Long uId;
	private Long pId;
	private Long mId;
	private Long bId;
	private Long rank;
	
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
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
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
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
