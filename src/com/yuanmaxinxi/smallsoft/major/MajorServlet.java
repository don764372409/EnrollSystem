package com.yuanmaxinxi.smallsoft.major;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/soft/major")
public class MajorServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private MajorService majorService;
	@Override
	public void init() throws ServletException {
		majorService = new MajorService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		if ("list".equals(cmd)) {
			//专业信息
			List<Major2> majors = majorService.selectAllByLayer(0);
		} else if ("firstmajor2".equals(cmd)) {
			String type = req.getParameter("type");
			System.out.println(type);
			List<Major2> list = majorService.selectAllByLayer(Integer.parseInt(type));
			putJson(list, resp);
		}else if("jianjie".equals(cmd)) {
			String id = req.getParameter("id");
			List<Major2> list = majorService.selectJianjie(Long.parseLong(id));
			String json = JSON.toJSONString(list);
			PrintWriter out = resp.getWriter();
			out.write(json);
		}
	}
}
