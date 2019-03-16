package com.yuanmaxinxi.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateTimeConverter implements Converter<String, Date> {
	public static final SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

	public Date convert(String dateStr) {//
		Date date = null;

		try {
			date = sdfDateTime.parse(dateStr);
		} catch (ParseException e) {
			try {
				date = sdfDate.parse(dateStr);
			} catch (ParseException e1) {
			}
		}

		return date;
	}
}
