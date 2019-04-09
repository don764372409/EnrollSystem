package com.yuanmaxinxi.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.DeptQueryPageDTO;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/user")
public class UserServlet extends BaseServlet{
	UserService userservice;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userservice=new UserService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if("select".equals(cmd)) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			int vip = Integer.parseInt(req.getParameter("vip"));
			User user = new User();
//			user.setId(id);
//			user.setUsername(name);
			user.setVip(vip);
//			DeptQueryPageDTO deptQuery=new DeptQueryPageDTO("t_user");
//			deptQuery.setId(id);
//			deptQuery.setName(name);
//			deptQuery.setVip(vip);
//			List<User> list=userservice.selectOneById(deptQuery);
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(req, resp);
			
		}else {
			List<User> list = userservice.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/user/user.jsp").forward(req, resp);	
		}
		
	}
}
