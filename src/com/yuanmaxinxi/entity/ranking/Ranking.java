package com.yuanmaxinxi.entity.ranking;
import java.math.BigDecimal;

import com.yuanmaxinxi.entity.university.University;
public class Ranking{
	private Long uId;//学校id
	private University uIdUniversity;
	private BigDecimal number;//武书连排名得分
	private int ranking;//武书连排名
	private int type;//类型
	@Override
	public String toString() {
		return "Ranking [uId=" + uId + ", uIdUniversity=" + uIdUniversity + ", number=" + number + ", ranking="
				+ ranking + ", type=" + type + "]";
	}
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public BigDecimal getNumber() {
		return number;
	}
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public University getuIdUniversity() {
		return uIdUniversity;
	}
	public void setuIdUniversity(University uIdUniversity) {
		this.uIdUniversity = uIdUniversity;
	}
	
}