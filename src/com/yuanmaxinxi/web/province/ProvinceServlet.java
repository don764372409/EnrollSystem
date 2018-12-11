package com.yuanmaxinxi.web.province;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.entity.admin.Admin;
import com.yuanmaxinxi.entity.province.Province;
import com.yuanmaxinxi.service.ProvinceService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/province")
public class ProvinceServlet extends BaseServlet{
	ProvinceService provinceservice;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		provinceservice=new ProvinceService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = req.getParameter("cmd");
		if ("showAdd".equals(cmd)) {
			
		}else if("add".equals(cmd)) {
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
		//nihao
		}else if("delete".equals(cmd)) {
			
		}else {
			//获取所有数据并跳转到列表页面
			List<Province> list = provinceservice.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/province.jsp").forward(req, resp);
			System.out.println("我是province");
		}
	}
}
