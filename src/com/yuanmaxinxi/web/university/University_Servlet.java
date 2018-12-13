package com.yuanmaxinxi.web.university;

import java.io.IOException;
<<<<<<< HEAD



import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
@WebServlet("/university")
public class University_Servlet extends HttpServlet{
	private UniversityService universityService;
	public static UniversityDao uniDao=new UniversityDao();
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("showAdd".equals(cmd)) {
			req.getRequestDispatcher("/WEB-INF/university/universityAdd.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}
		else {
			//获取所有数据并跳转页面
			List<University> universities = universityService.selectAll();
			req.setAttribute("unis", universities);
			req.getRequestDispatcher("/WEB-INF/university/university.jsp").forward(req, resp);
		}
	}
}
=======
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
@WebServlet("/university")

public class University_Servlet extends HttpServlet{
	private UniversityService universityService;
	public static UniversityDao uniDao=new UniversityDao();
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if("showAdd".equals(cmd)) {
			req.getRequestDispatcher("/WEB-INF/university/universityAdd.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}
		else {
			//获取所有数据并跳转页面
			List<University> universities = universityService.selectAll();
			req.setAttribute("unis", universities);
			req.getRequestDispatcher("/WEB-INF/university/university.jsp").forward(req, resp);
		}
	}
}
>>>>>>> branch 'master' of https://github.com/don764372409/EnrollSystem.git
