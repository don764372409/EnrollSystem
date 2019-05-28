/**
 * 
 */
package com.yuanmaxinxi.entity.xx;

import java.io.Serializable;

/**
 * @author 灰飞的猪
 *
 */
public class Signature implements Serializable {
	 private static final long serialVersionUID = -7799030247222127708L;
	 private String url;
	 private String jsapi_ticket;
	 private String nonceStr;
	 private String timestamp;
	 private String signature;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
}
