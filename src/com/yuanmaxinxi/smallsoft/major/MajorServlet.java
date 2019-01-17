package com.yuanmaxinxi.smallsoft.major;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/soft/major")
public class MajorServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("list".equals(cmd)) {
			//专业信息
			
		}
	}
}
