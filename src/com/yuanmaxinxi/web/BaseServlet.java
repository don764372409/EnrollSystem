package com.yuanmaxinxi.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * 将对象以json字符串的格式输出
	 * @param obj
	 * @param resp
	 * @return
	 */
	public String putJson(Object obj,HttpServletResponse resp) {
		resp.setContentType("text/json;charset=utf-8");
		return JSON.toJSONString(obj,SerializerFeature.WRITE_MAP_NULL_FEATURES);
	}
}
