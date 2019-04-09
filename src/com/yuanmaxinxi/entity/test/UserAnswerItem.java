package com.yuanmaxinxi.entity.test;

public class UserAnswerItem {
	private Long id;
	private Long topicId;
	private Long answerId;
	private String type;
	private Long uaId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUaId() {
		return uaId;
	}
	public void setUaId(Long uaId) {
		this.uaId = uaId;
	}
}
