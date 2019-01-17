package com.yuanmaxinxi.dto;

import com.yuanmaxinxi.entity.user.User;

public class DeptQueryPageDTO extends BaseQueryPageDTO<User>{
	private String name;
	private int id;
	private int vip;
	public DeptQueryPageDTO() {
		super("t_dept");
		
	}
	public void coverSqls() {
		if(name!=null&&!"".equals(name)) {
			getSqls().add("username=?");
			getParams().add(name);
		}
		if(id!=0&&id>0) {
			getSqls().add("id=?");	
			getParams().add(id);
		}
		if(vip>=0&&vip<=1) {
			getSqls().add("vip=?");
			getParams().add(vip);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
}
