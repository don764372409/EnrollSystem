package com.yuanmaxinxi.dto.university;

public class UniversityDTO {
	private Long id;
	private Long pId;//FK ʡ�����
	private String address;//���ڵ�		
	private Long quality;//FK �����ֵ���ԺУˮƽ����		
	private Long type;//FK �����ֵ���ԺУ���ͷ���		
	private String remark;//���		
	private int ranking;//ԺУ����		
	private String teachers;//ʦ���Ŷ�,�ٶȰٿ���ȡ		
	private Long record;//FK �����ֵ���ԺУѧ�� 
	private String subject;//ѧ�ƽ���(�費��Ҫ�浽�ֵ���,�ݶ�����)		
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
}
