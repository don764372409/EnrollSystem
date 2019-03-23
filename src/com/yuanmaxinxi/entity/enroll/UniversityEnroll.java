package com.yuanmaxinxi.entity.enroll;

import java.util.List;

public class UniversityEnroll {
	private Long uId;
	private Long bId;
	private Long mId;
	private Long pScore;
	private List<ScoreEnroll> ses;
	
	public Long getmId() {
		return mId;
	}
	public void setmId(Long mId) {
		this.mId = mId;
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
	}
	public List<ScoreEnroll> getSes() {
		return ses;
	}
	public void setSes(List<ScoreEnroll> ses) {
		this.ses = ses;
	}
	public Long getpScore() {
		return pScore;
	}
	public void setpScore(Long pScore) {
		this.pScore = pScore;
	}
}
