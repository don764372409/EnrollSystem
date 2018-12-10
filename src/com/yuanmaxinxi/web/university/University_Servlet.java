package com.yuanmaxinxi.web.university;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dao.university.UniversityDao;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.util.DBUtil;
@WebServlet("/cmd")
public class University_Servlet extends HttpServlet{
	public static UniversityDao uniDao=new UniversityDao();
	public static DBUtil dbutil=new DBUtil();
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(false) {
		}
		else {
			List<University> universities = uniDao.selectAll();
			req.setAttribute("uni", universities);
			req.getRequestDispatcher("university.jsp").forward(req, resp);
		}
	}
}
