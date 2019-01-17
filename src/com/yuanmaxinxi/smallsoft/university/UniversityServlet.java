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
			String str1 = req.getParameter("pageNum");
			int pageNum = Integer.parseInt(str1);
			System.out.println(pageNum);
			String str2 = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(str2);
			System.out.println(pageSize);
			
			//小程序  学校列表 应该有高级查询+分页
			System.err.println("后台接受请求");
			UniversityQueryPageDTO dto = new UniversityQueryPageDTO();
			List<University> list = universityService.queryPage(dto,pageNum,pageSize);//0-10条数据
			putJson(list, resp);
		}
	}
}
