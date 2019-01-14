package com.yuanmaxinxi.smallsoft.comparison;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/comparison")
public class ComparisonServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
		
	@Override
	public void init() throws ServletException {
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
			}
}
