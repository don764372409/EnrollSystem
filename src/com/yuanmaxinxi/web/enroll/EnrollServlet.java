package com.yuanmaxinxi.web.enroll;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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
import com.yuanmaxinxi.util.StringUtil;
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
	
	/*
	 * 从页面获取值并封装为Enroll对象
	 */
	private Enroll getEnroll(HttpServletRequest req) throws NumberFormatException {
		Enroll e = new Enroll();
		
		String id = req.getParameter("id");
		if(StringUtil.isNotNullAndEmpty(id)) {
			e.setId(Long.parseLong(id));
		}
		e.setuId(Long.parseLong(req.getParameter("uId")));
		e.setmId(Long.parseLong(req.getParameter("mId")));
		e.setBatch(req.getParameter("batch"));
		
		e.setNumber(Integer.parseInt(req.getParameter("number")));
		String minNumber=req.getParameter("minNumber");
		e.setMinNumber(StringUtil.isNullOrEmpty(minNumber)?0:Integer.parseInt(minNumber));
		String maxNumber=req.getParameter("maxNumber");
		e.setMaxNumber(StringUtil.isNullOrEmpty(maxNumber)?0:Integer.parseInt(maxNumber));
		String avgNumber=req.getParameter("avgNumber");
		e.setAvgNumber(StringUtil.isNullOrEmpty(avgNumber)?0:Integer.parseInt(avgNumber));
		String minRanking=req.getParameter("minRanking");
		e.setMinRanking(StringUtil.isNullOrEmpty(minRanking)?0:Integer.parseInt(minRanking));
		String maxRanking=req.getParameter("maxRanking");
		e.setMaxRanking(StringUtil.isNullOrEmpty(maxRanking)?0:Integer.parseInt(maxRanking));
		String avgRanking=req.getParameter("avgRanking");
		e.setAvgRanking(StringUtil.isNullOrEmpty(avgRanking)?0:Integer.parseInt(avgRanking));
		e.setTime(new Date());
		e.setTuition(new BigDecimal(req.getParameter("tuition")));
		e.setStudyYear(new BigDecimal(req.getParameter("studyYear")));
		return e;
	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		System.err.println(cmd);
		if ("showAdd".equals(cmd)) {
			req.setAttribute("ulist", us.selectAll());
			req.setAttribute("mlist", ms.selectAll());
			req.getRequestDispatcher("/WEB-INF/enroll/add.jsp").forward(req, resp);
		}else if("add".equals(cmd)) {
			ResultDTO dto;
			try {
				es.insert(getEnroll(req));
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("showEdit".equals(cmd)) {
			String idStr = req.getParameter("id");
			if (StringUtil.isNotNullAndEmpty(idStr)) {
				req.setAttribute("obj", es.selectOneById(Long.parseLong(idStr)));
				req.setAttribute("ulist", us.selectAll());
				req.setAttribute("mlist", ms.selectAll());
				req.getRequestDispatcher("/WEB-INF/enroll/edit.jsp").forward(req, resp);
			}else {
				resp.sendRedirect("/enroll");
			}
		}else if("edit".equals(cmd)) {
			ResultDTO dto;
			try {
				es.insert(getEnroll(req));
				dto = ResultDTO.newInstance(true, "添加成功!");
			} catch (Exception e) {
				e.printStackTrace();
				dto = ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else if("delete".equals(cmd)) {
			ResultDTO dto;
			try {
				es.delete(Long.parseLong(req.getParameter("id")));
				dto=ResultDTO.newInstance(true, "删除成功");
			}catch (Exception e) {
				dto=ResultDTO.newInstance(false, e.getMessage());
			}
			putJson(dto, resp);
		}else {
			//获取所有数据并跳转到列表页面
			List<Enroll> list = es.selectAll();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/enroll/list.jsp").forward(req, resp);
		}
	}
}