package com.yuanmaxinxi.entity.university;

import java.util.List;

import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.entity.university.jianzhang.Jianzhang;

public class University{
	private Long id;
	private Long pId;//省外键
	private String name;//院校名称
	private String address;//所在地		
	private String type;//FK 参照字典表的院校类型分类  普通本科  高职高专	
	private String property;//学校类型  理工类  综合类
	private String remark;//简介		
	private int ranking;//院校排名		
	private String nature;//公办 民办
	private String record; //FK 参照字典表的院校学历  
	private int f211;//是否是211学院  0-不是  1-是
	private int f985;//是否是985学院  0-不是  1-是
	private String guanwang;//官网
	private String code;//院校代码
	private String dept;//部门 
	private String teachers;//	师资团队,百度百科爬取
	private String subject;	//	学科建设(需不需要存到字典中,暂定不存)
	private String imgSrc;
	private List<Jianzhang> list;
	private Province pro;//省份
	
	public List<Jianzhang> getList() {
		return list;
	}
	public void setList(List<Jianzhang> list) {
		this.list = list;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
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
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public Province getPro() {
		return pro;
	}
	public void setPro(Province pro) {
		this.pro = pro;
	}
	public int getF211() {
		return f211;
	}
	public void setF211(int f211) {
		this.f211 = f211;
	}
	public int getF985() {
		return f985;
	}
	public void setF985(int f985) {
		this.f985 = f985;
	}
	public String getGuanwang() {
		return guanwang;
	}
	public void setGuanwang(String guanwang) {
		this.guanwang = guanwang;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
