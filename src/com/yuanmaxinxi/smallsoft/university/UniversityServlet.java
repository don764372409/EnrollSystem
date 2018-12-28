package com.yuanmaxinxi.smallsoft.university;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/university")
public class UniversityServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UniversityService universityService;
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("detail".equals(cmd)) {
			
		}else {
			//小程序  学校列表 应该有高级查询+分页
			UniversityQueryPageDTO dto = new UniversityQueryPageDTO();
			List<University> list = universityService.queryPage(dto);
			putJson(list, resp);
		}
	}
}
