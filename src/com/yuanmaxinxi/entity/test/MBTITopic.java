package com.yuanmaxinxi.entity.test;

import java.util.ArrayList;
import java.util.List;

public class MBTITopic {
	private Long id;
	private String content;
	private int type;
	private List<MBTIAnswer> answers = new ArrayList<>();
	
	public List<MBTIAnswer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<MBTIAnswer> answers) {
		this.answers = answers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
