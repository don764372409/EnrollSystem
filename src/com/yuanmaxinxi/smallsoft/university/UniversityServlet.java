package com.yuanmaxinxi.smallsoft.university;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/university")
public class UniversityServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UniversityService universityService;
	private UserService userService;
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		
		if ("search".equals(cmd)) {
			
		}else if("login".equals(cmd)){
			try {
				
			} catch (Exception e) {
				String name = req.getParameter("value");
				universityService.selectOneByName(name);
			}
		}else {
			//接受请求参数
			//处理数据
			//响应
			String str1 = req.getParameter("pageNum");
			int pageNum = Integer.parseInt(str1);
			String str2 = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(str2);
			UniversityQueryPageDTO dto = new UniversityQueryPageDTO();
			List<University> list = universityService.queryPage(dto,pageNum,pageSize);//0-10条数据
			putJson(list, resp);
		}
	}
}
