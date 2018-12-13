
package com.yuanmaxinxi.web.major;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.major.Major;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/major")
public class MajorServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private MajorService majorService;
	public void init() throws ServletException {
		 majorService = new MajorService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if ("showAdd".equals(cmd)) {
			
		}else if("add".equals(cmd)) {
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("delete".equals(cmd)) {
			
		}else {
			//获取所有数据并跳转到列表页面
			List<Major> list = majorService.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/major/list.jsp").forward(req, resp);
	}
	
}
}

