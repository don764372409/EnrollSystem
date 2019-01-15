package com.yuanmaxinxi.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {
	private static String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
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
