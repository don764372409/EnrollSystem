package com.yuanmaxinxi.dto;

import java.util.ArrayList;
import java.util.List;

import com.yuanmaxinxi.entity.user.User;

public class DeptQueryPageDTO extends BaseQueryPageDTO<User>{
	private String name;
	private int id;
	private int vip;
	public DeptQueryPageDTO(String tableName) {
		super(tableName);
		
		// TODO Auto-generated constructor stub
	}
	public void coverSqls() {
		if(name!=null&&!"".equals(name)) {
			sqls.add(" username=? ");
			params.add(name);
		}
		if(id!=0&&id>0) {
			sqls.add(" id=? ");	
			params.add(id);
		}
		if(vip>=0&&vip<=1) {
			sqls.add(" vip=? ");
			params.add(vip);
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
