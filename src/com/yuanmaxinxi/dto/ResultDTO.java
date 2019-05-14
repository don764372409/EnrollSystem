package com.yuanmaxinxi.dto;


public class ResultDTO {
	private boolean result;
	private String msg;
	private Object obj;//当前携带的数据对象
	private ResultDTO() {
	}
	public static ResultDTO newInstance(boolean result,String msg) {
		ResultDTO dto = new ResultDTO();
		dto.result = result;
		dto.msg = msg;
		return dto;
	}
	public static ResultDTO newInstance(Object obj) {
		ResultDTO dto = new ResultDTO();
		dto.obj = obj;
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
	public static ResultDTO putSuccessObj(String msg, Object obj) {
		ResultDTO dto = new ResultDTO();
		dto.setResult(true);
		dto.setObj(obj);
		dto.setMsg(msg);
		return dto;
	}
	public static ResultDTO putSuccess(String msg) {
		ResultDTO dto = new ResultDTO();
		dto.setResult(true);
		dto.setMsg(msg);
		return dto;
	}
	public static ResultDTO putError(String msg) {
		return newInstance(false,msg);
	}
}
