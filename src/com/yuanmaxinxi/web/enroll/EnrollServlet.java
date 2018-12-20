package com.yuanmaxinxi.web.enroll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuanmaxinxi.dto.ResultDTO;
import com.yuanmaxinxi.entity.enroll.Enroll;
import com.yuanmaxinxi.service.EnrollService;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.service.UniversityService;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/enroll")
public class EnrollServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private EnrollService es;
	private UniversityService us;
	private MajorService ms;
	public void init() throws ServletException {
		es = EnrollService.getDictionaryService();
		us = new UniversityService();
		ms = new MajorService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		System.err.println(cmd);
		if ("showAdd".equals(cmd)) {
			req.setAttribute("ulist", us.selectAll());
			req.setAttribute("mlist", ms.selectAll());
			req.getRequestDispatcher("/WEB-INF/enroll/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			//封装
			Enroll enroll = new Enroll();
			ResultDTO dto;
			try {
				es.insert(enroll);
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			String idStr = req.getParameter("id");
			Enroll enroll = new Enroll();
			
			ResultDTO dto;
			try {
				es.insert(enroll);
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("delete".equals(cmd)) {
			String idStr = req.getParameter("id");
		}else {
			//获取所有数据并跳转到列表页面
			List<Enroll> list = es.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/enroll/list.jsp").forward(req, resp);
		}
	}
}