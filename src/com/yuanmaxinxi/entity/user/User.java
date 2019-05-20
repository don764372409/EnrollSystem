package com.yuanmaxinxi.entity.user;

public class User {
	private Long id;
	private String url;//头像
	private String name;//昵称
	private int vip;//是否是VIP
	private String code;//自己的专属邀请码
	private String number;//填写别人的邀请码
	private String openid;
	private String fen;
	
	public String getFen() {
		return fen;
	}
	public void setFen(String fen) {
		this.fen = fen;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", url=" + url + ", name=" + name + ", vip=" + vip + ", code=" + code + ", number="
				+ number + ", openid=" + openid + ", fen=" + fen + "]";
	}
}
