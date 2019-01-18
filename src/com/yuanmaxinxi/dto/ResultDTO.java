package com.yuanmaxinxi.dto;

public class ResultDTO {
	private boolean result;
	private String msg;
	private static ResultDTO dto = new ResultDTO();
	private Object obj;//当前携带的数据对象
	private ResultDTO() {
	}
	public static ResultDTO newInstance(boolean result,String msg) {
		dto.result = result;
		dto.msg = msg;
		return dto;
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
