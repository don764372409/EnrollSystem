package com.yuanmaxinxi.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {
	public static String getEndWidth(String str) {
		int i = str.lastIndexOf(".");
		return str.substring(i, str.length());
	}
	public static boolean isNotNullAndEmpty(String str) {
		return str!=null&&!"".equals(str.trim());
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
	 public static String majorNoFilter(String str) throws PatternSyntaxException { 
		 String regEx="[^x00-xff]"; 
		 Pattern p = Pattern.compile(regEx); 
		 Matcher m = p.matcher(str);
		 return m.replaceAll("").trim();
	 }
	 public static void main(String[] args) {
		System.err.println("\u914d\u7f6e\u6587\u4ef6\u521d\u59cb\u5316\u5931\u8d25");
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
	 
}
