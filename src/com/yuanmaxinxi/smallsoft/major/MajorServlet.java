package com.yuanmaxinxi.smallsoft.major;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yuanmaxinxi.dto.MajorQueryPageDTO;
import com.yuanmaxinxi.entity.major.Major2;
import com.yuanmaxinxi.service.MajorService;
import com.yuanmaxinxi.util.StringUtil;
import com.yuanmaxinxi.web.BaseServlet;
@WebServlet("/soft/major")
public class MajorServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private MajorService majorService;
	@Override
	public void init() throws ServletException {
		majorService = new MajorService();
	}
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		if ("list".equals(cmd)) {
			//专业信息
			List<Major2> majors = majorService.selectAllByLayer(0);
		} else if ("firstmajor2".equals(cmd)) {
			String type = req.getParameter("type");
			System.out.println(type);
			List<Major2> list = majorService.selectAllByLayer(Integer.parseInt(type));
			putJson(list, resp);
		}else if("selectOneById".equals(cmd)) {
			String id = req.getParameter("id");
			Major2 mj = majorService.selectOneById(Long.parseLong(id));
			putJson(mj, resp);
		}else if("query".equals(cmd)) {
			MajorQueryPageDTO dto = new MajorQueryPageDTO();
			String str1 = req.getParameter("currentPage");
			int currentPage = Integer.parseInt(StringUtil.isNotNullAndEmpty(str1)?str1:"1");
			String str2 = req.getParameter("pageSize");
			int pageSize = Integer.parseInt(StringUtil.isNotNullAndEmpty(str2)?str2:"10");
			dto.setCurrentPage(currentPage);
			dto.setPageSize(pageSize);
			dto.setName(req.getParameter("name"));
			majorService.queryPage(dto);
			putJson(dto, resp);
		}
	}
}
