package com.yuanmaxinxi.entity.occupation;
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
	private String workContent;//工作内容
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
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	
}
