package com.yuanmaxinxi.entity.ulogpay;
import java.util.Date;
import java.math.BigDecimal;

public class Ulogpay{
	private Long id;
	private Long uId;//用户外键
	private String title;//支付标题
	private Long number;//进入流水的订单号
	private int paytype;//1-微信操作 2-支付宝操作 
	private BigDecimal value;//真实发生的金额 可以为负数 默认为元
	private Date paytime;//流水发生的时间
	private Integer type;//流水类型1-充值 2-支付消费 3-提现 4-退款
	private String openId;//openId
	
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
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
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setNumber(Long number){
		this.number=number;
	}
	public Long getNumber(){
		return this.number;
	}
	public void setPaytype(int paytype){
		this.paytype=paytype;
	}
	public int getPaytype(){
		return this.paytype;
	}
	public void setValue(BigDecimal value){
		this.value=value;
	}
	public BigDecimal getValue(){
		return this.value;
	}
	public void setPaytime(Date paytime){
		this.paytime=paytime;
	}
	public Date getPaytime(){
		return this.paytime;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return this.type;
	}
	@Override
	public String toString() {
		return "Ulogpay [id=" + id + ", uId=" + uId + ", title=" + title + ", number=" + number + ", paytype=" + paytype
				+ ", value=" + value + ", paytime=" + paytime + ", type=" + type + ", openId=" + openId + "]";
	}
}