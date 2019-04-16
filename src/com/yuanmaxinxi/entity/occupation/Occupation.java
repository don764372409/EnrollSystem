package com.yuanmaxinxi.entity.occupation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 职业表
 *
 */
public class Occupation {
	private Long id;
	private String name;
	private Long pId;//父级职业
	private String remark;//职业简介
	private List<Occupation> children = new ArrayList<>();
	private Occupation oc;
	
	public Occupation getOc() {
		return oc;
	}
	public void setOc(Occupation oc) {
		this.oc = oc;
	}
	public List<Occupation> getChildren() {
		return children;
	}
	public void setChildren(List<Occupation> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Occupation [id=" + id + ", name=" + name + ", pId=" + pId + ", remark=" + remark + ", children="
				+ children + "]";
	}
}
