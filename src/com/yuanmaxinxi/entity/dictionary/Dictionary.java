package com.yuanmaxinxi.entity.dictionary;

import com.yuanmaxinxi.entity.dictionaryType.DictionaryType;

public class Dictionary {
	private long id;
	private String name;
	private long typeId;
	private DictionaryType dt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public DictionaryType getDt() {
		return dt;
	}
	public void setDt(DictionaryType dt) {
		this.dt = dt;
	}

}
