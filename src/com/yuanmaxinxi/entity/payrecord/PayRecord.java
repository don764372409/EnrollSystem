package com.yuanmaxinxi.entity.payrecord;

import java.math.BigDecimal;
import java.util.Date;

public class PayRecord {
	private int id;
	private int uId;
	private String time;
	private BigDecimal money;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}