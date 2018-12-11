package com.yuanmaxinxi.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.security.MD5Encoder;

public class MD5Util {
	public static String encode(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(str.getBytes("utf-8"));
			String newStr=MD5Encoder.encode(bs);
			return newStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
