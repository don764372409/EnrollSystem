package com.yuanmaxinxi.util;

import java.net.MalformedURLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	private static String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
	/**
	 * 验证手机号码
	 * 
	 * @param str
	 * @return true 符合格式 false 不符合格式
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	 /**
  * 获取文件后缀名
  */
	public static String getEndFix(String fileName) {
		  return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	public static String getEndWidth(String str) {
		int i = str.lastIndexOf(".");
		return str.substring(i, str.length());
	}
	/**
	 * 给一个请求对象返回一个 域名 带项目上下文
	 * @param request
	 * @return
	 */
	public static String getHost(HttpServletRequest request) {
		java.net.URL url = null;
		try {
			url = new java.net.URL(request.getRequestURL().toString());
		} catch (MalformedURLException e1) {
			throw new RuntimeException("上传文件失败.errorCode:2002");
		}
		StringBuilder sb = new StringBuilder();
		String type = url.getProtocol();
//		if("http".equals(type)) {
//			type="https";
//		}
		String host = sb.append(type).append("://").append(url.getHost())+request.getContextPath()
		.toString();
		return host;
	}
	/**
	 * 给一个请求对象返回一个请求ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = "";
		ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || "".equals(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	public static boolean isNotNullAndEmpty(String str) {
		return str!=null&&!"".equals(str.trim())&&!"undefined".equals(str);
	}
	public static boolean isNullOrEmpty(String str) {
		return str==null||"".equals(str.trim());
	}
	/**
	    * 将短时间格式字符串转换为时间 yyyy-MM-dd 
	    * 
	    * @param strDate
	   * @return
	    */
	 public static Date strToDate(String strDate) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    ParsePosition pos = new ParsePosition(0);
	    Date strtodate = formatter.parse(strDate, pos);
	    return strtodate;
	 }
	 public static String majorDataContentFilter(String str) throws PatternSyntaxException { 
		 int i = str.indexOf("data-content");
		 int j = str.indexOf("href=");
		 
		 return str.substring(i+14, j-1);
	 } 
	 public static String majorNameFilter(String str) throws PatternSyntaxException { 
			String regEx="\\([0-9]*\\)|\\（[0-9]*\\）|\\([0-9]*）"; 
			Pattern p = Pattern.compile(regEx); 
			Matcher m = p.matcher(str);
			return m.replaceAll("").trim();
	} 
	 public static String majorNo2Filter(String str) throws PatternSyntaxException { 
		 String regEx="[^x00-xff]|\\?|[a-zA-Z]|=*"; 
		 Pattern p = Pattern.compile(regEx); 
		 Matcher m = p.matcher(str);
		 return m.replaceAll("").trim();
	 } 
	 public static String majorNoFilter(String str) throws PatternSyntaxException { 
		 String regEx="[^x00-xff]"; 
		 Pattern p = Pattern.compile(regEx); 
		 Matcher m = p.matcher(str);
		 return m.replaceAll("").trim();
	 }
	 public static void main(String[] args) {
		System.err.println(majorNameFilter("哲学(011)"));
	}
	 /*判断是否是数字*/
	 public static boolean isNumeric(String str){
		    for (int i = str.length();--i>=0;){  
		        if (!Character.isDigit(str.charAt(i))){
		             return false;
		         }
		     }
		    return true;
		 }
	 /**
	 * 日期转换成字符串
	 * @param date
	 * @return str
	 */
	 public static String DateToStr(Date date) {
	       
	     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     String str = format.format(date);
	     return str;
	 }
	 /**
	  * 指定长度的随机数
	  * @param length
	  * @return
	  */
	 public static String getRandomStrByLength(int length) {
		String str = "";
		Random rd = new Random();
		for (int i = 0; i < length; i++) {
			int index = rd.nextInt(nums.length);
			str+=nums[index];
		}
		return str;
	 }
}
