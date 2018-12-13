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
			req.getRequestDispatcher("/WEB-INF/major/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			String name = req.getParameter("name");
//			Long pId = Long.parseLong(req.getParameter("pId"));
//			Long type =Long.parseLong(req.getParameter("type"));
			String remark = req.getParameter("remark");
			String explain = req.getParameter("majorExplain");
			String ranking = req.getParameter("ranking");
			//封装
			Major major = new Major();
			major.setName(name);
//			major.setpId(pId);
//			major.setType(type);
			major.setRemark(remark);
			major.setMajorExplain(explain);
			major.setRanking(Integer.parseInt(ranking));
//			req.getRequestDispatcher("/major").forward(req, resp);
			//调用方法
			ResultDTO dto;
			try {
				majorService.insert(major);
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
//			resp.sendRedirect("major");
			
		}else if("showEdit".equals(cmd)) {
			
		}else if("edit".equals(cmd)) {
			
		}else if("delete".equals(cmd)) {
//			Long id = req.getParameter("id");
//			majorService.delete(id);
			resp.sendRedirect("major");
		}else {
			//获取所有数据并跳转到列表页面
			List<Major> list = majorService.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/major/list.jsp").forward(req, resp);
	}
	
}
}