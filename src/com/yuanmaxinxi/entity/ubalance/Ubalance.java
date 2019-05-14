package com.yuanmaxinxi.entity.ubalance;
import java.math.BigDecimal;

public class Ubalance{
	private Long id;
	private Long uId;//用户外键表
	private BigDecimal money;//用户余额

	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return this.id;
	}
	public void setuId(Long uId){
		this.uId=uId;
	}
	public Long getuId(){
		return this.uId;
	}
	public void setMoney(BigDecimal money){
		this.money=money;
	}
	public BigDecimal getMoney(){
		return this.money;
	}
	@Override
	public String toString() {
		return "Ubalance [id=" + id + ", uId=" + uId + ", money=" + money + "]";
	}
}