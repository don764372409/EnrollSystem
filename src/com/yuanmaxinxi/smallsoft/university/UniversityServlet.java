package com.yuanmaxinxi.smallsoft.university;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.dto.universitydto.UniversityQueryPageDTO;
import com.yuanmaxinxi.entity.university.University;
import com.yuanmaxinxi.entity.user.User;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.service.UserService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;

@WebServlet("/soft/university")
public class UniversityServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private UniversityService universityService;
	private UserService userService;
	private ResultDTO dto;
	private static String wheresql = "";
	@Override
	public void init() throws ServletException {
		universityService = new UniversityService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		//搜索查询
		if ("search".equals(cmd)) {
			try {
				String name = req.getParameter("name");
				System.out.println(name);
				if(StringUtil.isNotNullAndEmpty(name)) {
					wheresql= " instr(name,\""+name+"\")";
				}
				resp.sendRedirect("/soft/university");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("login".equals(cmd)){
			
		}else {
			//接受请求参数
			//处理数据
			//响应
			System.out.println("进入后台请求，院校信息");
			String str1 = req.getParameter("pageNum");
			int pageNum = Integer.parseInt(str1);
			String str2 = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(str2);
			List<University> list = universityService.queryPage(wheresql,pageNum,pageSize);//0-10条数据
			putJson(list, resp);
		}
	}
}
