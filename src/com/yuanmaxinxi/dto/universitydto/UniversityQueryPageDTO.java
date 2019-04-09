package com.yuanmaxinxi.dto.universitydto;

import com.yuanmaxinxi.dto.MyBatisQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;

public class UniversityQueryPageDTO extends MyBatisQueryPageDTO<University>{
	private String name;//学校名称
	private String record;//学校学历
	private String property;//类型
	private Long pId;//省外键
	private int f211 = -1;
	private int f985 = -1;
	private int ranking1;//开始排名
	private int ranking2;//结束排名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
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

	public int getRanking1() {
		return ranking1;
	}

	public void setRanking1(int ranking1) {
		this.ranking1 = ranking1;
	}

	public int getRanking2() {
		return ranking2;
	}

	public void setRanking2(int ranking2) {
		this.ranking2 = ranking2;
	}
}
