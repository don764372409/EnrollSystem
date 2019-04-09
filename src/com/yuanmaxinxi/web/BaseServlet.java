package com.yuanmaxinxi.web;

import java.io.IOException;
import java.io.PrintWriter;

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
	 * @throws IOException 
	 */
	public void putJson(Object obj,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		String json = JSON.toJSONString(obj,SerializerFeature.DisableCircularReferenceDetect);
		System.err.println(json);
		PrintWriter out = resp.getWriter();
		out.write(json);
		out.flush();
	}
}
