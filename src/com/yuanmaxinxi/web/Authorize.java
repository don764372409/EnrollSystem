/**
 *@晓风猪猪 
 *@param:
 *@data:2019年1月11日
 */
package com.yuanmaxinxi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shouquan")
public class Authorize extends BaseServlet{
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		System.err.println("后台接受请求");
	}
}
