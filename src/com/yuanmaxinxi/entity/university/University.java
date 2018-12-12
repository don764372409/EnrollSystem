package com.yuanmaxinxi.entity.university;

public class University{
	private Long id;
	private Long pId;//省外键
	private String name;
	private String address;//所在地		
		
	private Long quality;//FK 参照字典表的院校水平分类		

	private Long type;//FK 参照字典表的院校类型分类		

	private String remark;//简介		

	private int ranking;//院校排名		

	private String teachers;//	师资团队,百度百科爬取		

	private Long record; //FK 参照字典表的院校学历 

	private String subject;	//	学科建设(需不需要存到字典中,暂定不存)		

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getQuality() {
		return quality;
	}
	public void setQuality(Long quality) {
		this.quality = quality;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public Long getRecord() {
		return record;
	}
	public void setRecord(Long record) {
		this.record = record;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
