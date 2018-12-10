package com.yuanmaxinxi.web.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.service.AdminService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/admin")
public class AdminServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	public void init() throws ServletException {
		adminService = new AdminService();
	}
	
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("showAdd".equals(cmd)) {
			
		}else if("add".equals(cmd)) {
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("delete".equals(cmd)) {
			
		}else {
			//获取所有数据并跳转到列表页面
			List<Admin> list = adminService.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/admin/list.jsp").forward(req, resp);
		}
	}
}
