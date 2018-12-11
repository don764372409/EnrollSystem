package com.yuanmaxinxi.entity.dictionary;

import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;

public class Dictionary {
	private int id;
	private String name;
	private int typeId;
	private DictionaryType dt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public DictionaryType getDt() {
		return dt;
	}
	public void setDt(DictionaryType dt) {
		this.dt = dt;
	}

}
